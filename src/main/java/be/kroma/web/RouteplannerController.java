package be.kroma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/route")
class RouteplannerController {

	private static final String ROUTEPLANNER = "routeplanner/search";
	
	@RequestMapping(method = RequestMethod.GET)
	String routeplanner(){
		return ROUTEPLANNER;
	}
}
