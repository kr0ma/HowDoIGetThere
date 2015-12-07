package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
abstract class Segment {
	
	@XmlAttribute
	private String kind;
	
	@XmlAttribute
	private boolean isImperial;
	
	@XmlAttribute
	private Float distance;
	
	@XmlAttribute
	private Float duration;
	
	@XmlElement(name = "IndicativePrice")
	private IndicativePrice indicativePrice;

	public String getKind() {
		return kind;
	}

	public boolean isImperial() {
		return isImperial;
	}

	public Float getDistance() {
		return distance;
	}
	
	public String getDurationString() {
		int hours = (int) (duration / 60);
		int minutes = (int) (duration % 60);
		return String.format("%dhrs %02dmin", hours, minutes);
	}

	public IndicativePrice getIndicativePrice() {
		return indicativePrice;
	}		
	
	public abstract String getOrigin();
	
	public abstract String getDestination();

	public Float getDuration() {
		return duration;
	}
	
}
