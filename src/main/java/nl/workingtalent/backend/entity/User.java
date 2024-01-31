package nl.workingtalent.backend.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import nl.workingtalent.backend.dto.TagDto;
import nl.workingtalent.backend.dto.UserDto;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100)
	private String firstName;
	
	@Column(nullable = false, length = 100)
	private String lastName;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false)
	private boolean admin;

	@OneToMany(mappedBy = "user")
	private List<Reservation> reservations;
	
	private String token;

	public User() {}

	public User(String firstName, String lastName, String email, String password, boolean admin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

	//Getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	//Methods

	public UserDto toDto() {
		UserDto u = new UserDto();
		u.setId(id);
		u.setAdmin(admin);
		u.setEmail(email);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		if (reservations == null) {
			u.setReservations(new ArrayList<Long>());
		}
		else {
			u.setReservations(reservations.stream().map(t -> t.getId()).collect(Collectors.toList()));
		}
		return u;
	}
}
