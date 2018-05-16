package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by piyush.b.kumar on May 16, 2018.
 */
@Controller
public class IndexController {
	
	@RequestMapping({"", "/", "index"})
	public String getIndexPage() {
		return "index";
	}

}
