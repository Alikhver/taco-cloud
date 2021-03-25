package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domain.Ingredient;

@Repository
public interface JpaIngredientRepository extends CrudRepository<Ingredient, String> {
}
