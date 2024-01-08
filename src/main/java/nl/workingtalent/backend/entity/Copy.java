package nl.workingtalent.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Copy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//	@ManyToOne
//	@JoinColumn(name = "book_id", nullable = false)
//	private long Book;
	
	@Column(nullable = false)
	private boolean available;

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

//	public long getBook() {
//		return bookId;
//	}
//
//	public void setBook(long bookId) {
//		this.bookId = bookId;
//	}
}
