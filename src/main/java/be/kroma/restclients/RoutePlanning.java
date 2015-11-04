package be.kroma.restclients;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SearchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponse {
	@XmlElement(name="Route")
	private List<Route> routes;

	public List<Route> getRoutes() {
		return routes;
	}
	
	
}
