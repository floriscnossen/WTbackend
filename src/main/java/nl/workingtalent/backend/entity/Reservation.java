package nl.workingtalent.backend.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import nl.workingtalent.backend.dto.CopyDto;
import nl.workingtalent.backend.dto.ReservationDto;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Copy copy;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false, length = 100)
	private Date date;
	
	@Column(nullable = true, length = 100)
	private String status;

	//Getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//Methods
	
	public ReservationDto toDto() {
		ReservationDto r = new ReservationDto();
		r.setId(id);
		r.setCopy(copy.toDto());
		r.setUser(user.toDto());
		r.setStatus(status);
		return r;
	}
	
}
