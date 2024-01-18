package nl.workingtalent.backend.entity;


import jakarta.persistence.*;
import nl.workingtalent.backend.dto.BookDto;
import nl.workingtalent.backend.dto.CopyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Copy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	@OneToMany(mappedBy = "copy")
	private List<Reservation> reservations;
	
	@Column(nullable = false)
	private boolean available;

    //Constructors
	public Copy() {}

	public Copy(Book book, boolean available) {
		this.book = book;
		this.available = available;
	}

	//Getters and setters
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	//Methods
	
	public CopyDto toDto() {
		CopyDto c = new CopyDto();
		c.setId(id);
		c.setAvailable(available);
		c.setBook(book.toDto());
		if (reservations == null) {
			c.setReservations(new ArrayList<Long>());
		}
		else {
			c.setReservations(reservations.stream().map(t -> t.getId()).collect(Collectors.toList()));
		}
		return c;
	}
}
