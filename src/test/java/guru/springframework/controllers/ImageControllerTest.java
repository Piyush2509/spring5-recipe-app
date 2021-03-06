package guru.springframework.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;

/**
 * Created by piyush.b.kumar on May 24, 2018.
 */
public class ImageControllerTest {

	@Mock
	ImageService imageService;

	@Mock
	RecipeService recipeService;

	ImageController imageController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageController = new ImageController(imageService, recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).setControllerAdvice(new ControllerExceptionHandler())
				.build();
	}

	@Test
	public void testShowUploadForm() throws Exception {
		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);

		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		// when
		mockMvc.perform(get("/recipe/1/image")).andExpect(status().isOk()).andExpect(model().attributeExists("recipe"));

		// then
		verify(recipeService, times(1)).findCommandById(anyLong());
	}

	@Test
	public void testSaveImage() throws Exception {
		// given
		MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
				"Spring Framework Guru".getBytes());

		// when
		mockMvc.perform(multipart("/recipe/1/image").file(multipartFile)).andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/recipe/1/show"));

		// then
		verify(imageService, times(1)).saveRecipeImage(anyLong(), any());
	}

	@Test
	public void testGetRecipeImage() throws Exception {
		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);

		String s = "fake image text";
		Byte[] bytesBoxed = new Byte[s.getBytes().length];

		int i = 0;
		for (byte primType : s.getBytes()) {
			bytesBoxed[i++] = primType;
		}

		recipeCommand.setImage(bytesBoxed);

		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		// when
		MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage")).andExpect(status().isOk())
				.andReturn().getResponse();

		// then
		byte[] responseBytes = response.getContentAsByteArray();
		assertEquals(s.getBytes().length, responseBytes.length);
	}

	@Test
	public void testGetRecipeImageNumberFormatException() throws Exception {
		mockMvc.perform(get("/recipe/xyz/recipeimage")).andExpect(status().isBadRequest())
				.andExpect(view().name("400error"));
	}

}
