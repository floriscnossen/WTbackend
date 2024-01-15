package nl.workingtalent.backend.dto;

import java.util.List;

public class UserDto {
	private long id;
	private String firstName;
	private String lastName;
	private String role;
	private String email;
	private boolean admin;
	private List<Long> reservations;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public List<Long> getReservations() {
		return reservations;
	}

	public void setReservations(List<Long> reservations) {
		this.reservations = reservations;
	}
}
