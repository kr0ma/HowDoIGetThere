package be.kroma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.kroma.restclients.Route;
import be.kroma.restclients.RoutePlanning;
import be.kroma.restclients.RouteplannerClient;

@Service
class RouteplannerServiceImpl implements RouteplannerService{
	
	private final RouteplannerClient routeplannerClient;
	
	@Autowired
	public RouteplannerServiceImpl(RouteplannerClient routeplannerClient) {
		this.routeplannerClient = routeplannerClient;
	}
	

	@Override
	public List<Route> getRoutes(String origin, String destination) {
		return routeplannerClient.getRoutes(origin, destination);
	}


	@Override
	public RoutePlanning getRoutePlanning(String origin, String destination) {
		return routeplannerClient.getRoutePlanning(origin, destination);
	}

}
