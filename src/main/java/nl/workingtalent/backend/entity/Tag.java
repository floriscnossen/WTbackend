package nl.workingtalent.backend.entity;

import jakarta.persistence.*;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Book> books;

    public Tag() {
        //Empty constructor
    }

    public Tag(String name) {
        //Constructor with name
        this.name = name;
    }

    //Getters and setters
    public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
