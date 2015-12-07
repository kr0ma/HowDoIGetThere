package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransitSegment extends Segment {

	@XmlAttribute
	private String subkind;
	
	@XmlAttribute
	private String sName;
	
	@XmlAttribute
	private String tName;

	public String getSubkind() {
		return subkind;
	}

	@Override
	public String getOrigin() {
		return sName;
	}

	@Override
	public String getDestination() {
		return tName;
	}		
	
}
