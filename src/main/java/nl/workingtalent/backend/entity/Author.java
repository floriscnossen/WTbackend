package nl.workingtalent.backend.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import nl.workingtalent.backend.dto.AuthorDto;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = true, length = 100)
    private int birthYear;
	
	@Column(nullable=true, length = 100)
	private String nationality;
	
	@OneToMany(mappedBy = "author")
	private List<Book> books;
	
	//Constructors
	public Author() {}
	
	public Author(String name) {
		this.name = name;
	}
	
	public Author(String name, int birthYear) {
		this.name = name;
		this.birthYear = birthYear;
	}

	public Author(String name, int birthYear, String nationality) {
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	//Methods
	
	public AuthorDto toDto() {
		AuthorDto a = new AuthorDto();
		a.setId(id);
		a.setBirthYear(birthYear);
		a.setName(name);
		a.setNationality(nationality);
		if (books == null) {
			a.setBooks(new ArrayList<Long>());
		}
		else {
			a.setBooks(books.stream().map(b -> b.getId()).collect(Collectors.toList()));
		}
		return a;
	}
}
