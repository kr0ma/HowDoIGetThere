package be.kroma.restclients;

import java.util.List;

public interface RouteplannerClient {
	List<Route> getRoutes(String origin, String destination);
	RoutePlanning getRoutePlanning(String origin, String destination);
}
