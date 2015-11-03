package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
class IndicativePrice {
	@XmlAttribute
	Float price;
	
	@XmlAttribute
	String currency;
}
