package be.kroma.restclients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class Rome2RioRouteplannerClient implements RouteplannerClient {

	private final static Logger logger = Logger.getLogger(Rome2RioRouteplannerClient.class.getName());

	private final static String ORIGIN = "origin";
	private final static String DESTINATION = "destination";

	private final String uriTemplate;
	private final RestTemplate restTemplate;

	@Autowired
	Rome2RioRouteplannerClient(@Value("${rome2rioURL}") String uriTemplate, RestTemplate restTemplate) {
		this.uriTemplate = uriTemplate;
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Route> getRoutes(String origin, String destination) {
		try {
			Map<String, String> urlVariables = new HashMap<>();{
				urlVariables.put(ORIGIN, origin);
				urlVariables.put(DESTINATION, destination);
			}
			
			SearchResponse searchResponse = restTemplate.getForObject(uriTemplate, SearchResponse.class, urlVariables);
			return searchResponse.getRoutes();
		} catch (Exception ex){
			logger.log(Level.SEVERE, "no response", ex);
		}
		return null;
	}
	
	public SearchResponse getSearchResponse(String origin, String destination){
		try {
			Map<String, String> urlVariables = new HashMap<>();{
				urlVariables.put(ORIGIN, origin);
				urlVariables.put(DESTINATION, destination);
			}
			
			SearchResponse searchResponse = restTemplate.getForObject(uriTemplate, SearchResponse.class, urlVariables);
			return searchResponse;
		} catch (Exception ex){
			logger.log(Level.SEVERE, "no response", ex);
		}
		return null;
	}

}
