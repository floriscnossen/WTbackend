package nl.workingtalent.backend.dto;

import java.util.List;

public class CopySaveDto {
	private long bookId;
	private boolean available;

	//Getters and setters
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
}
