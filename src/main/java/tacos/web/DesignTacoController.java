package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Ingredient;
import tacos.domain.Order;
import tacos.domain.Taco;
import tacos.repository.jpa.JpaIngredientRepository;
import tacos.repository.jpa.JpaTacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    public static final String DESIGN_VIEW_NAME = "design";

    private final JpaIngredientRepository ingredientRepository;
    private final JpaTacoRepository tacoRepository;

    public DesignTacoController(JpaIngredientRepository ingredientRepository,
                                JpaTacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), ingredients.stream()
                    .filter(it -> it.getType().name().equals(type.name()))
                    .collect(Collectors.toList()));
        }

        model.addAttribute("design", new Taco());

        return DESIGN_VIEW_NAME;
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return DESIGN_VIEW_NAME;
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        log.info("Processing design: " + design);
        //go to http get request to this path.
        return "redirect:/orders/current";
    }
}
