package be.kroma.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.services.RouteplannerService;

@Controller
@RequestMapping("/route")
class RouteplannerController {

	private static final String ROUTEPLANNER = "routeplanner/search";

	private final RouteplannerService routeplannerService;

	@Autowired
	public RouteplannerController(RouteplannerService routeplannerService) {
		this.routeplannerService = routeplannerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView routeplanner() {
		return new ModelAndView(ROUTEPLANNER).addObject(new SearchForm());
	}

	@RequestMapping(path = "search", method = RequestMethod.GET)
	ModelAndView getRoute(@Valid SearchForm searchForm) {
		return new ModelAndView(ROUTEPLANNER, "routeplanning",
				routeplannerService.getRoutePlanning(searchForm.getOrigin(), searchForm.getDestination()))
						.addObject(new SearchForm());
	}

}
