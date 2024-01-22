package nl.workingtalent.backend.entity;

import jakarta.persistence.*;
import nl.workingtalent.backend.controller.AuthorController;
import nl.workingtalent.backend.dto.BookDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false, name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Copy> copy;

    private String description;
    @ManyToMany
    @JoinTable(name= "Book_Tags")
    private List<Tag> tags;
    private LocalDate releaseDate;
    private String isbnNumber;
    private String publisher;
    private int pageCount;

//    TODO: voor de implementatie hiervan hebben we een Course class nodig. Dit komt evtl. later.
    private String relatedCourses;
    private String format;
    
    @OneToMany(mappedBy = "book")
    private List<Copy> copies;
  
    private String info;
    private float rating;
    private String edition;
    
    private String imageUrl;

    
    //Constructors
    public Book() { }

    public Book(String title,
                Author author,
                String description,
                List<Tag> tags,
                LocalDate releaseDate,
                String isbnNumber,
                String publisher,
                int pageCount,
                String relatedCourses,
                String format,
                String info,
                float rating,
                String edition,
                String imageUrl) {
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
        this.imageUrl = imageUrl;
    }

    //Getters & Setters
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

	public List<Copy> getCopy() {
		return copy;
	}

	public void setCopy(List<Copy> copy) {
		this.copy = copy;
	}

	public List<Copy> getCopies() {
		return copies;
	}

	public void setCopies(List<Copy> copies) {
		this.copies = copies;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	//Methods

	public BookDto toDto() {
		BookDto b = new BookDto();
		b.setId(id);
		b.setAuthor(author.toDto());
		b.setDescription(description);
		b.setTitle(title);
		b.setEdition(edition);
		b.setInfo(info);
		b.setFormat(format);
		b.setIsbnNumber(isbnNumber);
		b.setPublisher(publisher);
		b.setPageCount(pageCount);
		if (tags == null) {
			b.setTags(new ArrayList<String>());
		}
		else {
			b.setTags(tags.stream().map(t -> t.getName()).collect(Collectors.toList()));
		}
		b.setRating(rating);
		b.setRelatedCourses(relatedCourses);
		if (copies == null) {
			b.setCopies(new ArrayList<Long>());
		}
		else {
			b.setCopies(copies.stream().map(c -> c.getId()).collect(Collectors.toList()));
		}
		return b;
	}
}
