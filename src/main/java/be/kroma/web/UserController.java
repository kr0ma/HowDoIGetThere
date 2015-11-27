package be.kroma.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.entities.User;
import be.kroma.services.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
class UserController {

	private static final String USER = "user";
	private static final String REGISTER = USER + "/register";

	private static final String PREFERENCES = USER + "/preferences";
	private static final String USERDETAILS = PREFERENCES + "/userdetailsPref";
	private static final String SEARCH = PREFERENCES + "/searchPref";

	private static final String REDIRECT_URL_AFTER_CREATE = "redirect:/";

	private final UserService userService;

	private final AuthenticationManager authManager;
	private final UserDetailsService userDetailsService;

	@Autowired
	UserController(UserService userService, @Qualifier("myAuthenticationManager") AuthenticationManager authManager,
			@Qualifier("myUserDetailsService") UserDetailsService userDetailsService) {
		this.userService = userService;
		this.authManager = authManager;
		this.userDetailsService = userDetailsService;
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	ModelAndView registrationForm() {
		return new ModelAndView(REGISTER, "user", new User());
	}

	// USER PREFERENCES

	@RequestMapping(path = "/preferences/userdetails", method = RequestMethod.GET)
	ModelAndView userDetailsPreferences(Principal principal) {		
		return new ModelAndView(USERDETAILS, "user", userService.findByUsername(principal.getName()));
	}

	@RequestMapping(path = "/preferences/userdetails", method = RequestMethod.POST)
	String userDetailsPreferences(@Validated({ User.UserDetails.class }) User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return USERDETAILS;
		}	
		userService.save(user);		
		return USERDETAILS;
	}

	@RequestMapping(path = "/preferences/search", method = RequestMethod.GET)
	ModelAndView searchPreferences() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return new ModelAndView(SEARCH, "user", userService.findByUsername(username));
	}

	// END USER PREFERENCES

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	String create(@Validated({ User.UserDetails.class }) User user, BindingResult bindingResult) {
		if (user.getPassword().length() > User.MAX_LENGTH_PASSWORD) {
			bindingResult.rejectValue("password", "passwordMaxLength", new Object[] { User.MAX_LENGTH_PASSWORD },
					"maximum " + User.MAX_LENGTH_PASSWORD + " characters");
		}
		if (bindingResult.hasErrors()) {
			return REGISTER;
		}

		// I need the unencrypted password for authencication after completed
		// registration
		String unencryptedUserPassword = user.getPassword();

		// create user
		try {
			userService.create(user);
		} catch (org.springframework.dao.DataIntegrityViolationException ex) {
			bindingResult.rejectValue("username", "usernameInUse", "username already exists");
			return REGISTER;
		}

		// auth user and redirect
		try {
			// login
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
					unencryptedUserPassword, userDetails.getAuthorities());			
			authManager.authenticate(auth);

			// auth succesfull -> store auth in session
			if (auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception ex) {
			System.out.println("something went wrong with auth");
		}
		return REDIRECT_URL_AFTER_CREATE;

	}

	@InitBinder("user")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
