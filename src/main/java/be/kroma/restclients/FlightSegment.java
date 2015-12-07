package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

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

}
