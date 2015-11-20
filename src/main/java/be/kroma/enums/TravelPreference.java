package be.kroma.enums;

public enum TravelPreference {
	DRIVE, TRAIN, BUS, FLY;	

	@Override
	public String toString() {		
		return super.toString().toLowerCase();
	}
	
	
}
