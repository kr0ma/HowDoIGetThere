package be.kroma.restclients;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

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

	@XmlElements({ @XmlElement(name = "CarSegment", type = CarSegment.class),
			@XmlElement(name = "WalkSegment", type = CarSegment.class),
			@XmlElement(name = "TransitSegment", type = TransitSegment.class),
			@XmlElement(name = "FlightSegment", type = FlightSegment.class) })
	private List<Segment> segments;

	public String getName() {
		return name;
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

	public List<Segment> getSegments() {
		return segments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Float getDuration() {
		return duration;
	}
}
