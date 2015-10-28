package be.kroma.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.kroma.valueobjects.Address;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotBlank
	@Length(min = 3, max = 50)
	@SafeHtml
	private String username;
	
	@NotBlank
	@Length(min = 6, max = 20)
	@SafeHtml
	private String password;
	
	@SuppressWarnings("unused")
	private boolean active;
	
	@NotBlank
	@Length(min = 2, max = 50)
	@SafeHtml
	private String name;
	
	@NotBlank
	@Length(min = 2, max = 50)
	@SafeHtml
	private String surname;
	
	@Valid
	@Embedded
	private Address address;
	
	public User(){
		this.active = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
}
