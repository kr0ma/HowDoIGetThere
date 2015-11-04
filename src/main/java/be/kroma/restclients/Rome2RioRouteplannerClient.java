package be.kroma.restclients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import be.kroma.exceptions.Rome2RioBadRequestException;

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
			Map<String, String> urlVariables = new HashMap<>();
			{
				urlVariables.put(ORIGIN, origin);
				urlVariables.put(DESTINATION, destination);
			}

			RoutePlanning searchResponse = restTemplate.getForObject(uriTemplate, RoutePlanning.class, urlVariables);
			return searchResponse.getRoutes();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "no response", ex);
		}
		return null;
	}

	public RoutePlanning getRoutePlanning(String origin, String destination) {
		try {
			Map<String, String> urlVariables = new HashMap<>();
			{
				urlVariables.put(ORIGIN, origin);
				urlVariables.put(DESTINATION, destination);
			}
			RoutePlanning searchResponse = restTemplate.getForObject(uriTemplate, RoutePlanning.class, urlVariables);
			return searchResponse;
		} catch (HttpStatusCodeException ex) {
			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				logger.log(Level.INFO, "Bad request: " + ex, ex.getResponseBodyAsString());
			}
			if (ex.getStatusCode().series() == Series.SERVER_ERROR) {
				logger.log(Level.SEVERE, "Rome2rioserver error: " + ex, ex.getResponseBodyAsString());
			}
		} catch (UnknownHttpStatusCodeException ex) {
			if (ex.getRawStatusCode() == 444) {
				logger.log(Level.INFO, "Bad request: " + ex, ex.getResponseBodyAsString());
				throw new Rome2RioBadRequestException(ex.getStatusText());
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Something went wrong", ex);
		}
		return null;
	}

}
