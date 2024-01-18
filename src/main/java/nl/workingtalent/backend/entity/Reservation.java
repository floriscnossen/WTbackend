package nl.workingtalent.backend.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import nl.workingtalent.backend.dto.ReservationDto;

@Entity
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
	private Date startDate;

	@Column(nullable = true, length = 100)
	private Date endDate;
	
	@Column(nullable = true, length = 100)
	private String status;
	
	//Constructors
	public Reservation() {}

	public Reservation(Copy copy, User user, Date startDate, Date endDate, String status) {
		this.copy = copy;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		r.setStartDate(startDate);
		r.setStatus(status);
		return r;
	}
	
}
