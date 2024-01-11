package nl.workingtalent.backend.service;

import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import jakarta.annotation.PostConstruct;
import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.repository.AuthorRepository;
import nl.workingtalent.backend.repository.BookRepository;

@Service
public class ReadCsvService {

	@Value("${APP_CSVFILE:}")
	private String appCsvFile;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostConstruct
	public void init() {
		readCsv();
	}
	
	public void readCsv() {
		System.out.println("Read csv file from " + this.appCsvFile);
		
		try { 
	        // Create an object of filereader class with CSV file as a parameter. 
	        FileReader filereader = new FileReader(appCsvFile); 

	        // create csvReader object passing file reader as a parameter 
	        CSVReader csvReader = new CSVReader(filereader); 
	        String[] nextRecord; 
	  
	        boolean skipLine = true;
	        // we are going to read data line by line 
	        while ((nextRecord = csvReader.readNext()) != null) {
	        	if (!skipLine) {
	        		Author author = new Author();
	        		author.setName(nextRecord[2]);
	        		author.setNationality("nl");
	        		authorRepository.save(author);

		            Book book = new Book();
		            book.setTitle(nextRecord[0]);
		            book.setIsbnNumber(nextRecord[1]);
		            book.setAuthor(author);
		            bookRepository.save(book);
	        	}
	            
	            skipLine = false;
	        } 
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    }		
	}
	
}
