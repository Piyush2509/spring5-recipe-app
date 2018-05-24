package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * Created by piyush.b.kumar on May 24, 2018.
 */
public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteRecipeIngredient(Long recipeId, Long ingredientId);
}
