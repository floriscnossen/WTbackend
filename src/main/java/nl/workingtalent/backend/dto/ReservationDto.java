package nl.workingtalent.backend.dto;

import java.time.LocalDate;
import java.util.Date;

public class ReservationDto {
	private long id;
	private CopyDto copy;
	private BookDto book;
	private UserDto user;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	
	//Constructors
	public ReservationDto() {}

	public ReservationDto(long id, CopyDto copy, BookDto book, UserDto user, LocalDate startDate, LocalDate endDate, String status) {
		this.id = id;
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

	public CopyDto getCopy() {
		return copy;
	}

	public void setCopy(CopyDto copy) {
		this.copy = copy;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public UserDto getUser() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
