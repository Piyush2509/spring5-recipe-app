package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by piyush.b.kumar on May 24, 2018.
 */
public interface ImageService {
	
	void saveRecipeImage(Long recipeId, MultipartFile imageFile);

}
