package nl.workingtalent.backend.entity;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import nl.workingtalent.backend.dto.ReservationDto;
import nl.workingtalent.backend.status.ReservationStatus;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Copy copy;

	@ManyToOne
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false, length = 100)
	private LocalDate startDate;

	private LocalDate endDate;
	
	@Enumerated(EnumType.ORDINAL)
	private ReservationStatus status;
	
	//Constructors
	public Reservation() {}

	public Reservation(User user, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Reservation(Copy copy, User user, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
		this.copy = copy;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Reservation(Copy copy, Book book, User user, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
		this.copy = copy;
		this.book = book;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	//Methods

//	public ReservationDto toDto() {
//		ReservationDto r = new ReservationDto();
//		r.setId(id);
//		r.setCopy(copy.toDto());
//		r.setUser(user.toDto());
//		r.setStartDate(startDate);
//		r.setStatus(status);
//		return r;
//	}
	
}
