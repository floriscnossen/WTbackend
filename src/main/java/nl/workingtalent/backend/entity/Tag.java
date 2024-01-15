package nl.workingtalent.backend.entity;

import jakarta.persistence.*;
import nl.workingtalent.backend.dto.ReservationDto;
import nl.workingtalent.backend.dto.TagDto;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
	
	//Methods
	
	public TagDto toDto() {
		TagDto t = new TagDto();
		t.setId(id);
		t.setName(name);
		t.setBooks(books.stream().map(b -> b.getId()).collect(Collectors.toList()));
		return t;
	}
}
