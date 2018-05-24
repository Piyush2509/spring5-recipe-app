package guru.springframework.services;

import java.util.Set;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

/**
 * Created by piyush.b.kumar on May 21, 2018.
 */
public interface RecipeService {
	
	Set<Recipe> getRecipes();
	
	Recipe findById(Long l);
	
	RecipeCommand findCommandById(Long l);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	
	void deleteById(Long l);

}
