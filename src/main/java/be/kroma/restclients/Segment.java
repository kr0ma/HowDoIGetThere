package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@DateTimeFormat(style=("-S"), pattern="hh'hrs 'mm'min'")
	public Long getDuration() {
		return duration.longValue();
	}

	public IndicativePrice getIndicativePrice() {
		return indicativePrice;
	}		
	
	public abstract String getOrigin();
	
	public abstract String getDestination();
	
}
