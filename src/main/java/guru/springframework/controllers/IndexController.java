package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

/**
 * Created by piyush.b.kumar on May 16, 2018.
 */
@Controller
public class IndexController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasurementRespository;	
	
	public IndexController(CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasurementRespository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasurementRespository = unitOfMeasurementRespository;
	}

	@RequestMapping({"", "/", "index"})
	public String getIndexPage() {
		
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasurementRespository.findByDescription("Teaspoon");
		
		System.out.println("Cat Id is:" + categoryOptional.get().getId());
		System.out.println("UOM Id is:" + unitOfMeasureOptional.get().getId());
		
		return "index";
	}

}
