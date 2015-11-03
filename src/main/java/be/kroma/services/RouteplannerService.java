package be.kroma.services;

import java.util.List;

import be.kroma.restclients.Route;

public interface RouteplannerService {
	List<Route> getRoutes(String origin, String destination);
}
