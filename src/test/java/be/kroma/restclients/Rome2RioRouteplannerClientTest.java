package be.kroma.restclients;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CreateRestClientBeans.class)
public class Rome2RioRouteplannerClientTest {
	
	private final String ORIGIN = "Bern";
	private final String DESTINATION = "Zurich";	
	
	@Autowired
	RouteplannerClient routeplannerClient;
	
	@Test
	public void getSearchResponseIsNotNull(){
		SearchResponse response=routeplannerClient.getSearchResponse(ORIGIN, DESTINATION);
		assertNotNull(response);
		System.out.println(response.getRoutes());
	}
	
	@Test
	public void getSearchResponseWithBadOriginIsNull(){
		assertNull(routeplannerClient.getSearchResponse("tddsfs", DESTINATION));
	}
	
	@Test
	public void getSearchResponseWithBadDestinationIsNull(){
		assertNull(routeplannerClient.getSearchResponse(ORIGIN, "tddsfs&oPos=lol"));
	}
	
	@Test
	public void getRoutesIsNotNull(){
		assertNotNull(routeplannerClient.getRoutes(ORIGIN, DESTINATION));		
	}
	
	
}
