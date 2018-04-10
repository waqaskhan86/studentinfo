package studentInformation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

//	@GetMapping("/")
//	public String Home() {
//		return "index";
//	}

	@GetMapping("/")
	public String home(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "index";
	}

}
