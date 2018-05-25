package guru.springframework.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.CategoryService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by piyush.b.kumar on May 22, 2018.
 */
@Slf4j
@Controller
public class RecipeController {

	private final RecipeService recipeService;

	private final CategoryService categoryService;

	public RecipeController(RecipeService recipeService, CategoryService categoryService) {
		this.recipeService = recipeService;
		this.categoryService = categoryService;
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
		model.addAttribute("categoryList", categoryService.listAllCategories());
		return "recipe/recipeform";
	}

	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		log.debug("Getting recipe form for update for id: " + id);
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		model.addAttribute("categoryList", categoryService.listAllCategories());
		return "recipe/recipeform";
	}

	@PostMapping("recipe")
	public String saveRecipe(@ModelAttribute RecipeCommand command, @RequestParam String[] categoryArray) {
		log.debug("Saving or updating recipe");
		Set<CategoryCommand> categoryList = categoryService.listAllCategories();
		List<String> list = Arrays.asList(categoryArray);
		Set<CategoryCommand> recipeCategories = categoryList.stream()
				.filter(category -> list.contains(category.getId().toString())).collect(Collectors.toSet());
		command.setCategories(recipeCategories);
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@GetMapping("/recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		log.debug("Deleting recipe: " + id);
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound() {
		log.error("Handling not found exception");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404error");
		return modelAndView;
	}

}
