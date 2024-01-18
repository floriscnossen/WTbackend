package nl.workingtalent.backend.dto;

import java.util.List;

import nl.workingtalent.backend.entity.Author;

public class AuthorDto {
	private long id;
	private String name;
    private int birthYear;
	private String nationality;
	private List<Long> books;
	
	// Constructors
	public AuthorDto() {}
	
	public AuthorDto(long id, String name, int birthYear, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.birthYear = birthYear;
		this.nationality = nationality;
	}

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

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Long> getBooks() {
		return books;
	}

	public void setBooks(List<Long> books) {
		this.books = books;
	}
	
	// Methods
	
	public Author toEntity() {
		Author a = new Author();
		a.setId(id);
		a.setBirthYear(birthYear);
		a.setName(name);
		a.setNationality(nationality);
		return a;
	}
}
