package be.kroma.services;

import java.util.List;

import be.kroma.restclients.Route;
import be.kroma.restclients.RoutePlanning;

public interface RouteplannerService {
	List<Route> getRoutes(String origin, String destination);
	RoutePlanning getRoutePlanning(String origin, String destination);
}
