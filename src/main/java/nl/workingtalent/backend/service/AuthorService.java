package nl.workingtalent.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired    //dependency injection
	AuthorRepository ar;
    
    public List<Author> getAuthors() {
		return ar.findAll();
	}
    
    public Optional<Author> getAuthorById(long id) {
		return ar.findById(id);
	}
    
    public Author addAuthor(Author author) {
    	return ar.save(author);
    }
    
    public Author updateAuthor(Author author) {
    	return ar.save(author);
    }
}
