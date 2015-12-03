package be.kroma.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import be.kroma.enums.TravelPreference;

class SearchPreferenceForm {
	@NotNull(message = "{required.travelPreferences}")
	private Set<TravelPreference> travelPreferences = new HashSet<>();
	
	SearchPreferenceForm() {
	}
	
	SearchPreferenceForm(Collection<TravelPreference> preferences) {
		travelPreferences.addAll(preferences);
	}

	public Set<TravelPreference> getTravelPreferences() {
		return travelPreferences;
	}

	public void setTravelPreferences(Set<TravelPreference> travelPreferences) {
		this.travelPreferences = travelPreferences;
	}

	

	
	
}
