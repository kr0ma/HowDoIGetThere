package be.kroma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index(String error) {
		System.out.println("error"+error);
		ModelAndView modelAndView = new ModelAndView(VIEW);
		return modelAndView;
	}
}
