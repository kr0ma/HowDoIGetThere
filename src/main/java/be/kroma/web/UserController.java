package be.kroma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
class UserController {

	private static final String USER = "user";
	private static final String REGISTER = USER + "/register";
	
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	String registration(){
		return REGISTER;
	}
}
