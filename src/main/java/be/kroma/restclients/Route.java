package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Route {
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private Float distance;
	
	@XmlAttribute
	private Float duration;
	
	@XmlElement(name = "IndicativePrice")
	private IndicativePrice indicativePrice;
		
	public String getName() {
		return name;
	}
	public Float getDistance() {
		return distance;
	}
	public Float getDuration() {
		return duration;
	}
	
	public IndicativePrice getIndicativePrice() {
		return indicativePrice;
	}
	
	
}
