package nl.workingtalent.backend.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = true, length = 100)
    private Date date;
	
	@Column(nullable=true, length = 100)
	private String nationality;

	@OneToMany(mappedBy = "author")
	private Set<Book> books;
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


}
