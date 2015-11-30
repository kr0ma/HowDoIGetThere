package be.kroma.enums;

import org.junit.Test;

import org.junit.Assert;

public class TravelPreferenceTest {
	@Test
	public void inKleineLetters() {
		Assert.assertEquals("drive", TravelPreference.DRIVE.toString());
	}
	@Test
	public void eenValueInKleineLetters() {
		Assert.assertEquals("drive", TravelPreference.values()[0].toString());
	}
	
	
	
	
}
