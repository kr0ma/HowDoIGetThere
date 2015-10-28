package be.kroma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";
	private static final String LOGIN = "login";
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index(String error) {
		ModelAndView modelAndView;
		if (error == null){
			modelAndView = new ModelAndView(VIEW);
		} else {
			modelAndView = new ModelAndView(LOGIN);
		}		
		return modelAndView;
	}
}
