package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.domain.Ingredient;
import tacos.repository.jpa.JpaIngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private JpaIngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(JpaIngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @Override
  public Ingredient convert(String id) {
    return ingredientRepo.findById(id).orElse(null);
  }

}
