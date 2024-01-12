package nl.workingtalent.backend.service;

import java.io.File;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
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
	@Value("${APP_CSVFILE:data_cleaned.csv}")
	private String appCsvFile;
	
	@Autowired
	AuthorRepository ar;
	
    @Autowired
    BookRepository br;
	
    @Autowired
    TagRepository tr;
    
	Map<String,Author> authorMap = new HashMap<>();

	Map<String,Tag> tagMap = new HashMap<>();

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
    	try {
            FileReader filereader = new FileReader(appCsvFile); 
            CSVReader csvReader = new CSVReader(filereader); 
            String[] nextRecord;

            csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
            	Book book = new Book();
            	book.setTitle(nextRecord[1]);
            	book.setAuthor(getAuthorByName(nextRecord[7], nextRecord[8]));
            	book.setDescription(nextRecord[2]);
            	
            	ObjectMapper objectMapper = new ObjectMapper();
            	String tagList = nextRecord[3];
            	tagList = tagList.replaceAll("'(?=([^\"]*[\"][^\"]*[\"])*[^\"]*$)", "\"");
            	List<String> tagNames = objectMapper.readValue(tagList, new TypeReference<List<String>>(){});
            	
            	List<Tag> tags = new ArrayList<>();
            	for (String tagName : tagNames) {
            		tags.add(getTagByName(tagName));
            	}
            	book.setTags(tags);
            	
            	// TODO: release_date;
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
    
    /* Returns the author with a given name or creates it.
     * TODO: Maybe use ar instead of authorMap. */
    public Author getAuthorByName(String authorName, String authorBirthYear) {
    	Author author;
    	if (authorMap.containsKey(authorName)) {
    		author = authorMap.get(authorName);
    	}
    	else {
    		author = new Author();
    		author.setName(authorName);
    		try { author.setBirthYear((int) Float.parseFloat(authorBirthYear));} catch (NumberFormatException e) {}
    		author = ar.save(author);
    		authorMap.put(authorName, author);
    	}
    	return author;
    }

    /* Returns the tag with a given name or creates it.
     * TODO: Maybe use tr instead of tagMap. */
    public Tag getTagByName(String tagName) {
    	Tag tag;
    	if (tagMap.containsKey(tagName)) {
    		tag = tagMap.get(tagName);
    	}
    	else {
    		tag = new Tag();
    		tag.setName(tagName);
    		tag = tr.save(tag);
    		tagMap.put(tagName, tag);
    	}
    	return tag;
    }
}
