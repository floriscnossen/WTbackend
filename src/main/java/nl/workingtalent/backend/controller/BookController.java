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
import nl.workingtalent.backend.dto.BookDto;
import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.service.BookService;

@RestController
@RequestMapping(path = "product/book")
public class BookController {
	@Autowired
	BookService bs;
	
	@GetMapping("all")
	public List<BookDto> getBooks() {
		return bs.getBooks().stream().map(b -> b.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<BookDto> getBookById(@PathVariable("id") long id) {
		return bs.getBookById(id).map(b -> b.toDto());
	}
	
	@GetMapping("title/{title}")
	public List<BookDto> getBooksByTitle(@PathVariable("title") String title) {
		return bs.getBooksByTitle(title).stream().map(b -> b.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("tag/{id}")
	public List<BookDto> getBooksByTagId(@PathVariable("id") long id) {
		return bs.getBooksByTagId(id).stream().map(b -> b.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("tag/name/{name}")
	public List<BookDto> getBooksByTagName(@PathVariable("name") String name) {
		return bs.getBooksByTagName(name).stream().map(b -> b.toDto()).collect(Collectors.toList());
	}
	
	
	
	@PostMapping
	public void addBook(@RequestBody Book a) {
		bs.addBook(a);
	}
	
	@PutMapping("{id}")
	public void updateBook(@PathVariable("id") long id, @RequestBody Book a) {
		a.setId(id);
		bs.updateBook(a);
	}
	
	@DeleteMapping("{id}")
	public void deleteBook(@PathVariable("id") long id) {
		bs.deleteBook(id);
	}
}
