package be.kroma.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	
	private final AuthenticationManager authManager;
	private final UserDetailsService userDetailsService;

	@Autowired
	UserController(UserService userService, @Qualifier("myAuthenticationManager")AuthenticationManager authManager,
				@Qualifier("myUserDetailsService") UserDetailsService userDetailsService) {
		this.userService = userService;
		this.authManager = authManager;
		this.userDetailsService = userDetailsService;
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	ModelAndView registrationForm() {
		return new ModelAndView(REGISTER, "user", new User());
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	String create(@Valid User user, BindingResult bindingResult) {
		if (user.getPassword().length() > User.MAX_LENGTH_PASSWORD){
			bindingResult.rejectValue("password", null, "Password length has to be lower than " + User.MAX_LENGTH_PASSWORD);
		}
		if (bindingResult.hasErrors()){			
			return REGISTER;
		}
		
		// I need the unencrypted password for authencication after completed registration
		String unencryptedUserPassword = user.getPassword();
		
		// create user
		try {			
			userService.create(user);			
		} catch (org.springframework.dao.DataIntegrityViolationException ex){
			bindingResult.rejectValue("username" , null, "Username reeds in gebruik");
			return REGISTER;
		}
		
		// auth user and redirect		
		try {
			// login
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, unencryptedUserPassword, userDetails.getAuthorities());
			authManager.authenticate(auth);
			
			// auth succesfull -> store auth in session			
			if(auth.isAuthenticated()) {
		        SecurityContextHolder.getContext().setAuthentication(auth);		        
		     }
		} catch (Exception ex){
			System.out.println("something went wrong with auth");
		}		
		return REDIRECT_URL_AFTER_CREATE;
		
	}

	@InitBinder("user")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
