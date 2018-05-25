package guru.springframework.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.converters.CategoryToCategoryCommand;
import guru.springframework.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by piyush.b.kumar on May 25, 2018.
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryToCategoryCommand categoryToCategoryCommand;

	public CategoryServiceImpl(CategoryRepository categoryRepository,
			CategoryToCategoryCommand categoryToCategoryCommand) {
		this.categoryRepository = categoryRepository;
		this.categoryToCategoryCommand = categoryToCategoryCommand;
	}

	@Override
	public Set<CategoryCommand> listAllCategories() {
		log.debug("I'm in the listAllCategories service");
		return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
				.map(categoryToCategoryCommand::convert).collect(Collectors.toSet());
	}

}
