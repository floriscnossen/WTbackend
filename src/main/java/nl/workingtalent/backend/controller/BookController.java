package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import nl.workingtalent.backend.dto.BookSaveDto;
import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.mapper.DtoMapper;
import nl.workingtalent.backend.service.BookService;

@RestController
@RequestMapping(path = "product/book")
@CrossOrigin
public class BookController {
	@Autowired
	BookService bs;
	
	@Autowired
	DtoMapper mapper;
	
	@GetMapping("all")
	public List<BookDto> getBooks() {
		return bs.getBooks().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<BookDto> getBookById(@PathVariable("id") long id) {
		return bs.getBookById(id).map(mapper::toDto);
	}
	
	@GetMapping("title/{title}")
	public List<BookDto> getBooksByTitle(@PathVariable("title") String title) {
		return bs.getBooksByTitle(title).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("tag/{id}")
	public List<BookDto> getBooksByTagId(@PathVariable("id") long id) {
		return bs.getBooksByTagId(id).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("tag/name/{name}")
	public List<BookDto> getBooksByTagName(@PathVariable("name") String name) {
		return bs.getBooksByTagName(name).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	
	//TODO
	@PostMapping
	public void addBook(@RequestBody BookSaveDto bookDto) {
		bs.addBook(mapper.toEntity(bookDto));
	}
	
	@PutMapping("{id}")
	public void updateBook(@PathVariable("id") long id, @RequestBody BookSaveDto bookDto) {
		Book book = mapper.toEntity(bookDto);
		book.setId(id);
		bs.updateBook(book);
	}
	
	@DeleteMapping("{id}")
	public void deleteBook(@PathVariable("id") long id) {
		bs.deleteBook(id);
	}
}
