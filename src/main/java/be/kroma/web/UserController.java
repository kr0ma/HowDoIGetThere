package be.kroma.web;

import java.security.Principal;
import java.util.EnumSet;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.entities.User;
import be.kroma.enums.TravelPreference;
import be.kroma.services.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
class UserController {

	private static final String USER = "user";
	private static final String REGISTER = USER + "/register";

	private static final String CONTROLPANEL = USER + "/controlpanel";
	private static final String USERDETAILS = CONTROLPANEL + "/usercontactinfo";
	private static final String SEARCH = CONTROLPANEL + "/searchPref";

	private static final String REDIRECT_URL_AFTER_CREATE = "redirect:/";
	
	private static final String REDIRECT_URL_AFTER_UPDATE_CONTROL_PANEL = "redirect:/user/controlpanel";
	private static final String REDIRECT_URL_AFTER_UPDATE_USER_DETAILS = REDIRECT_URL_AFTER_UPDATE_CONTROL_PANEL + "/userdetails";
	private static final String REDIRECT_URL_AFTER_UPDATE_TRAVEL_PREFERENCES = REDIRECT_URL_AFTER_UPDATE_CONTROL_PANEL + "/search";

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
		//USER DETAILS
	@RequestMapping(path = "/controlpanel/userdetails", method = RequestMethod.GET)
	ModelAndView userContactInfo(Principal principal) {
		return new ModelAndView(USERDETAILS, "user", userService.findByUsername(principal.getName()));
	}

	@RequestMapping(path = "/controlpanel/userdetails", method = RequestMethod.POST)
	String userContactInfo(@ModelAttribute @Validated({ User.UserDetails.class }) User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return USERDETAILS;
		}
		userService.save(user);
		return REDIRECT_URL_AFTER_UPDATE_USER_DETAILS;
	}
		
		// SEARCHPREFERENCES
	@RequestMapping(path = "/controlpanel/search", method = RequestMethod.GET)
	ModelAndView searchPreferences(Principal principal, @ModelAttribute("user") User loggedInUser) {			
		return new ModelAndView(SEARCH, "travelPreferences", EnumSet.allOf(TravelPreference.class)).addObject(new SearchPreferenceForm(loggedInUser.getSearchPreferences()));
	}
	
	@RequestMapping(path = "/controlpanel/search", method = RequestMethod.POST)
	ModelAndView SearchPreferences(@Valid SearchPreferenceForm searchPreferenceForm, BindingResult bindingResult, @ModelAttribute("user") User loggedInUser){
		if (bindingResult.hasErrors()) {
			return new ModelAndView(SEARCH, "travelPreferences", EnumSet.allOf(TravelPreference.class));
		}		
		loggedInUser.setSearchPreferences(searchPreferenceForm.getTravelPreferences());
		userService.save(loggedInUser);
		return new ModelAndView(REDIRECT_URL_AFTER_UPDATE_TRAVEL_PREFERENCES);
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
	void initBinderUser(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
	
	@InitBinder("searchPreferenceForm")
	void initBinderSearchPreferenceForm(WebDataBinder binder){
		binder.initDirectFieldAccess();
	}
}
