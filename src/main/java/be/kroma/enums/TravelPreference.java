package be.kroma.enums;

public enum TravelPreference {
	DRIVE, TRAIN, BUS, FLY;

	@Override
	public String toString() {
		switch (this.name()) {
		case "DRIVE":	
			return "car";
		case "FLY":
			return "flight";
		default:
			return super.toString().toLowerCase();
		}
	}
	
	
	
}
