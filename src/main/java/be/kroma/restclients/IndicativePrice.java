package be.kroma.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
class IndicativePrice {
	
	@XmlAttribute
	private Float price;
	
	@XmlAttribute
	private String currency;	

	public Float getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency;
	}
}
