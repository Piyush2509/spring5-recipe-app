package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		log.debug("Getting recipe page for id: " + id);
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}

	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		log.debug("Getting new recipe form");
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		log.debug("Getting recipe form for update for id: " + id);
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}

	@PostMapping("recipe")
	public String saveRecipe(@ModelAttribute RecipeCommand command) {
		log.debug("Saving or updating recipe");
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@GetMapping("/recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		log.debug("Deleting recipe: " + id);
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}

}
