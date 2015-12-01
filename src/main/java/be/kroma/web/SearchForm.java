package be.kroma.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.kroma.enums.TravelPreference;

class SearchForm {

	@NotBlank
	@SafeHtml
	private String origin;

	@NotBlank
	@SafeHtml
	private String destination;

	@NotNull(message = "{required.searchForm.travelPreferences}")
	private Set<TravelPreference> travelPreferences = new HashSet<>();

	SearchForm() {
	}

	SearchForm(Collection<TravelPreference> preferences) {
		travelPreferences.addAll(preferences);
	}

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

	public Set<TravelPreference> getTravelPreferences() {
		return travelPreferences;
	}

	public void setTravelPreferences(Set<TravelPreference> travelPreferences) {
		this.travelPreferences = travelPreferences;
	}

}
