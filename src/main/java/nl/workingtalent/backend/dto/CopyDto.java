package nl.workingtalent.backend.dto;

import java.util.List;

public class CopyDto {
	private long id;
	private BookDto book;
	private List<Long> reservations;
	private boolean available;

    //Constructors
	public CopyDto() {}

	public CopyDto(long id, BookDto book, boolean available) {
		this.id = id;
		this.book = book;
		this.available = available;
	}

	//Getters and setters
	public List<Long> getReservations() {
		return reservations;
	}

	public void setReservations(List<Long> reservations) {
		this.reservations = reservations;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}
}
