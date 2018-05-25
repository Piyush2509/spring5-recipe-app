package guru.springframework.services;

import java.util.Set;

import guru.springframework.commands.CategoryCommand;

/**
 * Created by piyush.b.kumar on May 25, 2018.
 */
public interface CategoryService {

	Set<CategoryCommand> listAllCategories();

}
