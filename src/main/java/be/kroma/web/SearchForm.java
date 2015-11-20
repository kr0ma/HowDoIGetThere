package be.kroma.web;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import be.kroma.enums.TravelPreference;

public class SearchForm {

	@NotBlank
	private String origin;

	@NotBlank
	private String destination;

	//@Size(min = 1, message="required.searchForm.travelPreferences")
	@NotNull(message="{required.searchForm.travelPreferences}")
	private List<TravelPreference> travelPreferences;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<TravelPreference> getTravelPreferences() {
		return travelPreferences;
	}

	public void setTravelPreferences(List<TravelPreference> travelPreferences) {
		this.travelPreferences = travelPreferences;
	}

}
