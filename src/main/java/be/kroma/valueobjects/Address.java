package be.kroma.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.kroma.constraints.Postcode;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String street;
	@NotBlank
	@Length(min = 1, max = 7)
	@SafeHtml
	private String houseNr;
	@NotNull
	@Postcode
	private Integer postcode;
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String city;

	public Address() {
	}

	public Address(String street, String houseNr, Integer postcode, String city) {
		this.street = street;
		this.houseNr = houseNr;
		this.postcode = postcode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNr() {
		return houseNr;
	}	

	public String getCity() {
		return city;
	}

	public Integer getPostcode() {
		return postcode;
	}


}
