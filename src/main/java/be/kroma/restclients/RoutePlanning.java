package be.kroma.restclients;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import be.kroma.enums.TravelPreference;

@XmlRootElement(name = "SearchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoutePlanning {
	@XmlElement(name = "Route")
	private List<Route> routes;

	@XmlElement(name = "Place")
	private List<Place> places;

	public List<Route> getRoutes() {
		return routes;
	}

	@SuppressWarnings("unused")
	private RoutePlanning() {
	}

	public RoutePlanning(RoutePlanning routePlanning) {
		this.places = routePlanning.getPlaces();
		this.routes = new ArrayList<>();
	}

	public Place getOriginPlace() {
		return places.get(0);
	}

	public Place getDestinationPlace() {
		return places.get(1);
	}

	private List<Place> getPlaces() {
		return places;
	}

	public void addRoutes(Set<Route> routes) {
		this.routes.addAll(routes);
	}

	public Set<Route> getRoutes(List<TravelPreference> myPreferences) {
		Set<Route> prefRoutes = new HashSet<>();
		Set<TravelPreference> travelpreferencesToExclude = EnumSet.allOf(TravelPreference.class);
		travelpreferencesToExclude.removeAll(myPreferences);		
		for (Route route : this.routes) {
			for (TravelPreference myPreference : myPreferences) {
				if ((route.getName().toLowerCase().contains(myPreference.name().toLowerCase()) ||
						route.getName().toLowerCase().equals("walk"))
						&& !(routeContainsTravelPreferenceToExclude(route, myPreference,
								travelpreferencesToExclude))) {					
					prefRoutes.add(route);
				}
			}
		}
		return prefRoutes;
	}

	private boolean routeContainsTravelPreferenceToExclude(Route route, TravelPreference myPreference,
			Set<TravelPreference> preferencesToCheck) {
		for (TravelPreference preferenceToCheck : preferencesToCheck) {
			if (route.getName().toLowerCase().contains(preferenceToCheck.name().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
