package be.kroma.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.entities.User;
import be.kroma.services.UserService;

@Controller
@RequestMapping("/user")
class UserController {

	private static final String USER = "user";
	private static final String REGISTER = USER + "/register";
	
	private static final String REDIRECT_URL_AFTER_CREATE = "redirect:/";
	
	private final UserService userService;

	@Autowired
	UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	ModelAndView registrationForm() {
		return new ModelAndView(REGISTER, "user", new User());
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	String create(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			return REGISTER;
		}
		try {
			userService.create(user);
			return REDIRECT_URL_AFTER_CREATE;
		} catch (org.springframework.dao.DataIntegrityViolationException ex){
			bindingResult.rejectValue("username" , null, "Username reeds in gebruik");
			return REGISTER;
		}
		
	}

	@InitBinder("user")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
