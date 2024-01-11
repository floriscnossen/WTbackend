package nl.workingtalent.backend.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import jakarta.annotation.PostConstruct;
import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.repository.AuthorRepository;
import nl.workingtalent.backend.repository.BookRepository;
import nl.workingtalent.backend.repository.TagRepository;

@Service
public class LoadCsvService {
	@Value("${APP_CSVFILE:C:\\Users\\flori\\OneDrive\\Documenten\\WorkingTalent\\Project\\data_cleaned.csv}")
	private String appCsvFile;
	
	@Autowired
	AuthorRepository ar;
	
    @Autowired
    BookRepository br;
	
    @Autowired
    TagRepository tr;

    @PostConstruct
    public void init() {
    	loadCsv();
    }
    
    /* Loads the data of the csv file "data_cleaned.csv".
     * It has columns 'id', 'title', 'description', 'tags', 'release_date', 
     *   'ISBN_number', 'publisher', 'author_name', 'author_birth_year', 
     *   'pages', 'related_courses', 'format', 'rating', edition'.
     */
    public void loadCsv() {
    	Map<String,Author> authorMap = new HashMap<>();
//    	Map<String,Tag> tagMap = new HashMap<>();
    	try { 
            FileReader filereader = new FileReader(appCsvFile); 
            CSVReader csvReader = new CSVReader(filereader); 
            String[] nextRecord;

            csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
            	String author_name = nextRecord[7];
            	Author author;
            	if (authorMap.containsKey(author_name)) {
            		author = authorMap.get(author_name);
            	}
            	else {
            		author = new Author();
            		author.setName(author_name);
            		try { author.setBirthYear(Integer.parseInt(nextRecord[8]));} catch (NumberFormatException e) {}
            		author = ar.save(author);
            		authorMap.put(author_name, author);
            	}
            	
            	Book book = new Book();
            	book.setTitle(nextRecord[1]);
            	book.setAuthor(author);
            	book.setDescription(nextRecord[2]);
            	// TODO: book.setReleaseDate();
            	book.setIsbnNumber(nextRecord[5]);
            	book.setPublisher(nextRecord[6]);
            	// TODO: 'pages', 'related_courses', 'format', 'rating', edition'
            	br.save(book);
            }
            csvReader.close();
        } 
        catch (Exception e) { 
            e.printStackTrace();
        }
    }
}
