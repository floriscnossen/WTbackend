package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.service.BookService;

@RestController
@RequestMapping(path = "product/book")
public class BookController {
	@Autowired
	BookService bs;
	
	@GetMapping("all")
	public List<Book> getBooks() {
		return bs.getBooks();
	}
	
	@GetMapping("{id}")
	public Optional<Book> getBookById(@PathVariable("id") long id) {
		return bs.getBookById(id);
	}
}
