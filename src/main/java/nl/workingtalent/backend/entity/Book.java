package nl.workingtalent.backend.entity;

import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(nullable = false, name = "author_id")
    private Author author;

    private String description;
    @ManyToMany
    @JoinTable(name= "Book_Tags")
    private Set<Tag> tags;
    private Date releaseDate;
    private String isbnNumber;
    private String publisher;
    private Integer pageCount;

//    TODO: voor de implementatie hiervan hebben we een Course class nodig. Dit komt evtl. later.
    private String relatedCourses;
    private String format;
    private String info;
    private String rating;
    private String edition;

    public Book() {
        //Empty constructor
    }

    public Book(String title,
                Author author,
                String description,
                Set<Tag> tags,
                Date releaseDate,
                String isbnNumber,
                String publisher,
                Integer pageCount,
                String relatedCourses,
                String format,
                String info,
                String rating,
                String edition) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.releaseDate = releaseDate;
        this.isbnNumber = isbnNumber;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.relatedCourses = relatedCourses;
        this.format = format;
        this.info = info;
        this.rating = rating;
        this.edition = edition;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
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

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getRelatedCourses() {
        return relatedCourses;
    }

    public void setRelatedCourses(String relatedCourses) {
        this.relatedCourses = relatedCourses;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
