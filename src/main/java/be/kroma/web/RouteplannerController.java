package be.kroma.web;

import java.security.Principal;
import java.util.EnumSet;

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
import be.kroma.enums.TravelPreference;
import be.kroma.exceptions.Rome2RioBadRequestException;
import be.kroma.restclients.RoutePlanning;
import be.kroma.services.RouteplannerService;
import be.kroma.services.UserService;

@Controller
@RequestMapping("/route")
class RouteplannerController {

	private static final String ROUTEPLANNER = "routeplanner/search";

	private final RouteplannerService routeplannerService;
	private final UserService userService;

	@Autowired
	public RouteplannerController(RouteplannerService routeplannerService, UserService userService) {
		this.routeplannerService = routeplannerService;
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView routeplanner(Principal principal) {
		User user = userService.findByUsernameWithPreferences(principal.getName());
		return new ModelAndView(ROUTEPLANNER).addObject(new SearchForm(user.getSearchPreferences())).addObject("travelPreferences",
				EnumSet.allOf(TravelPreference.class));
	}

	@RequestMapping(path = "search", method = RequestMethod.GET)
	ModelAndView getRoute(@Valid SearchForm searchForm, BindingResult bindingResult) {
		RoutePlanning routePlanningRequest = null;
		if (bindingResult.hasErrors()) {
			return new ModelAndView(ROUTEPLANNER).addObject("travelPreferences", EnumSet.allOf(TravelPreference.class));
		}
		try {
			routePlanningRequest = routeplannerService.getRoutePlanning(searchForm.getOrigin(),
					searchForm.getDestination());
			RoutePlanning routePlanning = new RoutePlanning(routePlanningRequest);
			routePlanning.addRoutes(routePlanningRequest.getRoutes(searchForm.getTravelPreferences()));
			return new ModelAndView(ROUTEPLANNER, "routeplanning", routePlanning).addObject("travelPreferences",
					EnumSet.allOf(TravelPreference.class));
		} catch (Rome2RioBadRequestException ex) {
			if (ex.getMessage().contains("origin")) {
				bindingResult.rejectValue("origin", "errorPlaceNotFound", "not found");
			} else {
				bindingResult.rejectValue("destination", "errorPlaceNotFound", "not found");
			}
			return new ModelAndView(ROUTEPLANNER).addObject("travelPreferences", EnumSet.allOf(TravelPreference.class));
		} catch (NullPointerException ex){			
			return new ModelAndView(ROUTEPLANNER).addObject("travelPreferences", EnumSet.allOf(TravelPreference.class));
		}

	}

	@InitBinder("searchForm")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

}
