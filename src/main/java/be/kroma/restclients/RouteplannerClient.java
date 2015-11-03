package be.kroma.restclients;

import java.util.List;

public interface RouteplannerClient {
	List<Route> getRoutes(String origin, String destination);
	SearchResponse getSearchResponse(String origin, String destination);
}
