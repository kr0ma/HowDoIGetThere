package be.kroma.restclients;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.kroma.exceptions.Rome2RioBadRequestException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CreateRestClientBeans.class)
public class Rome2RioRouteplannerClientTest {

	private final String ORIGIN = "Bern";
	private final String DESTINATION = "Zurich";

	@Autowired
	RouteplannerClient routeplannerClient;

	private RoutePlanning routePlanning;	

	@Before
	public void before() {		
		routePlanning = routeplannerClient.getRoutePlanning(ORIGIN, DESTINATION);
	}

	@Test(expected = Rome2RioBadRequestException.class)
	public void getSearchResponseWithBadOriginThrowsRome2RioBadRequestException() {
		assertNull(routeplannerClient.getRoutePlanning("tddsfs", DESTINATION));
	}

	@Test(expected = Rome2RioBadRequestException.class)
	public void getSearchResponseWithBadDestinationThrowsRome2RioBadRequestException() {
		assertNull(routeplannerClient.getRoutePlanning(ORIGIN, "tddsfs"));
	}
	
	// TEST ROOTELEMENT RESPONSE
	@Test
	public void getSearchResponseIsNotNull() {
		assertNotNull(routePlanning);
	}

	// TEST ROUTE ELEMENT FROM RESPONSE
	@Test
	public void getRoutesIsNotNull() {
		assertNotNull(routePlanning.getRoutes());
	}

	@Test
	public void getRoute1GetNameIsNotNull() {
		assertNotNull(routePlanning.getRoutes().get(0).getName());
	}

	@Test
	public void getRoute1GetDistanceIsNotNull() {
		assertNotNull(routePlanning.getRoutes().get(0).getDistance());
	}

	@Test
	public void getRoute1GetDurationIsNotNull() {
		assertNotNull(routePlanning.getRoutes().get(0).getDuration());
	}

	// TEST INDICATIVE PRICE FROM ROUTE
	@Test
	public void getRoute1GetIndicativePriceIsNotNull() {
		assertNotNull(routePlanning.getRoutes().get(0).getIndicativePrice());
	}
	
	@Test
	public void getRoute1GetIndicativePriceGetPriceIsNotNull() {
		System.out.println(routePlanning.getRoutes().get(0).getIndicativePrice().getPrice());
		assertNotNull(routePlanning.getRoutes().get(0).getIndicativePrice().getPrice());
	}
	
	@Test
	public void getRoute1GetIndicativePriceGetCurrencyIsNotNull() {
		System.out.println(routePlanning.getRoutes().get(0).getIndicativePrice().getCurrency());
		assertNotNull(routePlanning.getRoutes().get(0).getIndicativePrice().getCurrency());
	}

}
