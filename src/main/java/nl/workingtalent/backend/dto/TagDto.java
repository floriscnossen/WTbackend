package nl.workingtalent.backend.dto;

import java.util.List;

public class TagDto {
    private long id;
    private String name;
    private List<Long> books;
    
    //Constructors
	public TagDto() {}

    public TagDto(long id, String name) {
		this.id = id;
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

    public List<Long> getBooks() {
        return books;
    }

    public void setBooks(List<Long> books) {
        this.books = books;
    }
}
