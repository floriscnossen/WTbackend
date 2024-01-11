package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.service.AuthorService;

@RestController
@RequestMapping(path = "author")
public class AuthorController {
	@Autowired
	AuthorService as;
	
	@GetMapping("all")
	public List<Author> getAuthors() {
		return as.getAuthors();
	}
	
	@GetMapping("{id}")
	public Optional<Author> getAuthorById(@PathVariable("id") long id) {
		return as.getAuthorById(id);
	}
}
