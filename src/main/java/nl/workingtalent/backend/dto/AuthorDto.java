package nl.workingtalent.backend.dto;

import java.util.List;

public class AuthorDto {
	private Long id;
	private String name;
    private Integer birthYear;
	private String nationality;
//	private List<Long> books;
	
	//Getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

//	public List<Long> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<Long> books) {
//		this.books = books;
//	}
}
