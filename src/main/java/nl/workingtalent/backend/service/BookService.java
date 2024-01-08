package nl.workingtalent.backend.service;

import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository br;

}
