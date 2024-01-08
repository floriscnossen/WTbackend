package nl.workingtalent.backend.entity;

import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;
    private String author;
    private String description;
    @ManyToMany
    private Set<Tag> tags;
    private Date releaseDate;
    private String IsbnNumber;
    private String publisher;
    private String format;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
