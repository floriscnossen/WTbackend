package nl.workingtalent.backend.service;

import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
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
    
    public List<Book> getBooksByTitle(String title) {
		return br.findByTitleLikeSorted("%" + title + "%", JpaSort.unsafe("LENGTH(title)"));
	}
    
    public List<Book> getBooksByTagId(long tagId) {
		return br.findByTagsId(tagId);
	}
    
    public List<Book> getBooksByTagName(String tagName) {
		return br.findByTagsName(tagName);
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
