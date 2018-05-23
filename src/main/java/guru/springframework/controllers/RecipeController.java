package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by piyush.b.kumar on May 22, 2018.
 */
@Slf4j
@Controller
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/show/{id}")
	public String ShowById(@PathVariable String id, Model model) {
		log.debug("Getting recipe page");
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}
	
	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		log.debug("Getting new recipe form");
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		log.debug("Saving or updating recipe");
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);	
		return "redirect:/recipe/show/" + savedCommand.getId();
	}

}
