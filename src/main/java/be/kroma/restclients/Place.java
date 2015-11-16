package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Place {

	@XmlAttribute
	private String kind;
	
	@XmlAttribute
	private String name;

	public String getKind() {
		return kind;
	}

	public String getName() {
		return name;
	}	
	
}
