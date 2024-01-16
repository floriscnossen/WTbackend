package nl.workingtalent.backend.service;

import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository br;
    
    public List<Book> getBooks() {
		return br.findAll();
	}
    
    public Optional<Book> getBookById(long id) {
		return br.findById(id);
	}
    
    public Book addBook(Book book) {
    	return br.save(book);
    }
    
    public Book updateBook(Book book) {
    	return br.save(book);
    }
    
    public void deleteBook(long id) {
    	br.deleteById(id);
    }
}
