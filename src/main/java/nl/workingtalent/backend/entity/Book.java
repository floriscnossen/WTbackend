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
    private String isbnNumber;
    private String publisher;
    private String format;

    public Book() {
        //Empty constructor
    }

    public Book(String title, String author, String description, Set<Tag> tags, Date releaseDate, String isbnNumber, String publisher) {
        //Constructor with all properties except format
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.releaseDate = releaseDate;
        this.isbnNumber = isbnNumber;
        this.publisher = publisher;
    }

    public Book(String title, String author, String description, Set<Tag> tags, Date releaseDate, String isbnNumber, String publisher, String format) {
        //Full constructor
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.releaseDate = releaseDate;
        this.isbnNumber = isbnNumber;
        this.publisher = publisher;
        this.format = format;
    }

    //Getters & Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
