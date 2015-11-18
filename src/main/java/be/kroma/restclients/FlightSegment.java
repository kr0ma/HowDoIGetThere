package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.springframework.format.annotation.DateTimeFormat;

@XmlAccessorType(XmlAccessType.FIELD)
public class FlightSegment extends Segment {
	
	@XmlAttribute
	private String sCode;
	
	@XmlAttribute
	private String tCode;

	public String getsCode() {
		return sCode;
	}

	public String gettCode() {
		return tCode;
	}

	@Override
	public String getOrigin() {
		return sCode;
	}

	@Override
	public String getDestination() {
		return tCode;
	}
	
	@Override
	@DateTimeFormat(style=("-S"), pattern="hh'hrs 'mm'min'")
	public Long getDuration() {
		return super.getDuration() * 60000 - 3600000;
	}	

}
