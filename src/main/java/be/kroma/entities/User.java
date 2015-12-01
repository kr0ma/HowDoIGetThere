package be.kroma.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.kroma.enums.TravelPreference;
import be.kroma.valueobjects.Address;

@Entity
@Table(name = "users")
@SecondaryTable(name = "userroles", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "userid") })
@NamedEntityGraph(name = "User.WithPreferences", attributeNodes= @NamedAttributeNode("searchPreferences"))
public class User implements Serializable {
	private static final long serialVersionUID = 1L;	

	public interface UserDetails extends Default {
	}

	public interface SearchPreferences {
	}

	@Transient
	public static final int MAX_LENGTH_PASSWORD = 20;

	@Id
	@GeneratedValue
	private long id;

	@NotBlank
	@Length(min = 3, max = 50, groups = UserDetails.class)
	@SafeHtml(groups = UserDetails.class)
	private String username;

	@NotBlank(groups = UserDetails.class)
	@Length(min = 6, max = 255, groups = UserDetails.class)
	@SafeHtml(groups = UserDetails.class)
	private String password;

	@NotBlank(groups = UserDetails.class)
	@Length(min = 2, max = 50, groups = UserDetails.class)
	@SafeHtml(groups = UserDetails.class)
	private String name;

	@NotBlank(groups = UserDetails.class)
	@Length(min = 2, max = 50, groups = UserDetails.class)
	@SafeHtml(groups = UserDetails.class)
	private String surname;

	@Valid
	@Embedded
	private Address address;

	// finals
	@Column(table = "userroles")
	private final int roleid = 2;

	@SuppressWarnings("unused")
	private final boolean active = true;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "usersearchpreferences", joinColumns = @JoinColumn(name = "userid"))
	@Column(name = "searchPreference")
	@Enumerated(EnumType.STRING)
	private Set<TravelPreference> searchPreferences = new HashSet<>(); 
	
	public User() {
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

	public Set<TravelPreference> getSearchPreferences() {
		return Collections.unmodifiableSet(searchPreferences);
	}

	public void setSearchPreferences(Set<TravelPreference> searchPreferences) {
		this.searchPreferences = searchPreferences;
	}

}
