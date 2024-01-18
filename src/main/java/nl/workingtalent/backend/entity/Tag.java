package nl.workingtalent.backend.entity;

import jakarta.persistence.*;
import nl.workingtalent.backend.dto.ReservationDto;
import nl.workingtalent.backend.dto.TagDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Book> books;

    //Constructors
    public Tag() {}

    public Tag(String name) {
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
		if (books == null) {
			t.setBooks(new ArrayList<Long>());
		}
		else {
			t.setBooks(books.stream().map(b -> b.getId()).collect(Collectors.toList()));
		}
		return t;
	}
}
