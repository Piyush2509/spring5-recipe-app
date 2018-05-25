package guru.springframework.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by piyush.b.kumar on May 24, 2018.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public void saveRecipeImage(Long recipeId, MultipartFile imageFile) {
		log.debug("Received a file");
	}

}
