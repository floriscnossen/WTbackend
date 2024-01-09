package nl.workingtalent.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.repository.AuthorRepository;

@Service
public class AuthorService {
	//Has a relatie
	@Autowired    //dependency injection
	AuthorRepository ar;
    
    public List<Author> getAuthors() {
		return ar.findAll();
	}
}
