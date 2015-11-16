package be.kroma.restclients;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import be.kroma.enums.TravelPreference;

@XmlRootElement(name="SearchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoutePlanning {
	@XmlElement(name="Route")
	private List<Route> routes;
	
	@XmlElement(name="Place")
	private List<Place> places;

	public List<Route> getRoutes() {
		return routes;
	}
	
	@SuppressWarnings("unused")
	private RoutePlanning(){}
	
	public RoutePlanning(RoutePlanning routePlanning){
		this.places = routePlanning.getPlaces();	
		this.routes = new ArrayList<>();
	}

	public Place getOriginPlace(){
		return places.get(0);
	}
	
	public Place getDestinationPlace(){
		return places.get(1);
	}

	private List<Place> getPlaces() {
		return places;
	}
	
	public void addRoutes(List<Route> routes){
		this.routes.addAll(routes);
	}
	
	public List<Route> getRoutes(List<TravelPreference> myPreferences){
		List<Route> prefRoutes = new ArrayList<>();	
		Set<TravelPreference> preferencesToCheck = EnumSet.allOf(TravelPreference.class); 
		preferencesToCheck.removeAll(myPreferences);
		
		for (TravelPreference preferenceToCheck : myPreferences){
			System.out.println("mypref: " + preferenceToCheck);
		}
		
		for (TravelPreference preferenceToCheck : preferencesToCheck){
			System.out.println("pref to check : " + preferenceToCheck);
		}
		
		for (Route route: this.routes){
			for (TravelPreference myPreference:myPreferences){				
				if (route.getName().toLowerCase().contains(myPreference.name().toLowerCase())){					
					for (TravelPreference preferenceToCheck : preferencesToCheck){
						//System.out.println("adding : " + route.getName() + " preference " + preferenceToCheck.name());
						if (!(route.getName().toLowerCase().contains(preferenceToCheck.name().toLowerCase()))){
							prefRoutes.add(route);
						}
					}					
				}
			}						
		}
		return prefRoutes; 
	}
	
	
	
}
