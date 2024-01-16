package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.AuthorDto;
import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.service.AuthorService;

@RestController
@RequestMapping(path = "author")
public class AuthorController {
	@Autowired
	AuthorService as;
	
	@GetMapping("all")
	public List<AuthorDto> getAuthors() {
		return as.getAuthors().stream().map(a -> a.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<AuthorDto> getAuthorById(@PathVariable("id") long id) {
		return as.getAuthorById(id).map(a -> a.toDto());
	}
	
	@PostMapping
	public void addAuthor(@RequestBody Author a) {
		as.addAuthor(a);
	}
	
	@PutMapping("{id}")
	public void updateAuthor(@PathVariable("id") long id, @RequestBody Author a) {
		a.setId(id);
		as.updateAuthor(a);
	}
	
	@DeleteMapping("{id}")
	public void updateAuthor(@PathVariable("id") long id) {
		as.deleteAuthor(id);
	}
}
