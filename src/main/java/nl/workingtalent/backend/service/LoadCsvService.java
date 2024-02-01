package nl.workingtalent.backend.service;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.annotation.PostConstruct;
import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.repository.AuthorRepository;
import nl.workingtalent.backend.repository.BookRepository;
import nl.workingtalent.backend.repository.CopyRepository;
import nl.workingtalent.backend.repository.ReservationRepository;
import nl.workingtalent.backend.repository.TagRepository;
import nl.workingtalent.backend.status.ReservationStatus;

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
	
    @Autowired
    CopyRepository cr;
	
    @Autowired
    ReservationRepository rr;
	
    @Autowired
    UserService us;
    
	Map<String,Author> authorMap = new HashMap<>();

	Map<String,Tag> tagMap = new HashMap<>();

    @PostConstruct
    public void init() {
    	if (br.count() < 50) {
    		loadCsv();
    	}
    	
    	// Create test users
    	User admin;
    	Optional<User> optionalAdmin = us.getUserByEmail("admin@admin.nl");
    	if (optionalAdmin.isEmpty()) {
        	String password1 = BCrypt.withDefaults().hashToString(10, "admin".toCharArray());
    		admin = new User("Admin", "", "admin@admin.nl", password1, true);
    		us.addUser(admin);
    	}
    	else {
    		admin = optionalAdmin.get();
    	}
    	
    	User trainee;
    	Optional<User> optionalTrainee = us.getUserByEmail("trainee@trainee.nl");
    	if (optionalTrainee.isEmpty()) {
	    	String password2 = BCrypt.withDefaults().hashToString(10, "trainee".toCharArray());
			trainee = new User("Trainee", "", "trainee@trainee.nl", password2, false);
			trainee.setId(2);
			us.addUser(trainee);
    	}
    	else {
    		trainee = optionalTrainee.get();
    	}
		
    	// Create test reservations
		Copy c1 = cr.findAll().get(0);
		Reservation r1 = new Reservation(c1, c1.getBook(), trainee, LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), ReservationStatus.RETURNED);
		r1.setId(1);
		rr.save(r1);
		Copy c2 = cr.findAll().get(1);
		Reservation r2 = new Reservation(c2, c2.getBook(), admin, LocalDate.now().minusDays(2), LocalDate.now().minusDays(1), ReservationStatus.RETURNED);
		r2.setId(2);
		rr.save(r2);
    }
    
    /* Loads the data of the csv file "data_cleaned.csv".
     * It has columns 'id', 'title', 'description', 'tags', 'release_date', 
     *   'ISBN_number', 'publisher', 'author_name', 'author_birth_year', 
     *   'pages', 'related_courses', 'format', 'rating', edition'.
     */
    public void loadCsv() {
    	rr.deleteAll();
    	cr.deleteAll();
    	br.deleteAll();
    	ar.deleteAll();
    	tr.deleteAll();
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
            	
            	book.setReleaseDate(LocalDate.parse(nextRecord[4]));
            	book.setIsbnNumber(nextRecord[5]);
            	if (!nextRecord[6].trim().isEmpty()) { book.setPublisher(nextRecord[6]); }
            	try { book.setPageCount((int) Float.parseFloat(nextRecord[9]));} catch (NumberFormatException e) {}
            	if (!nextRecord[10].trim().isEmpty()) { book.setRelatedCourses(nextRecord[10]); }
            	if (!nextRecord[11].trim().isEmpty()) { book.setFormat(nextRecord[11]); }
            	try { book.setRating(Float.parseFloat(nextRecord[12]));} catch (NumberFormatException e) {}
            	if (!nextRecord[13].trim().isEmpty()) { book.setEdition(nextRecord[13]); }
            	
            	book = br.save(book);
            	Copy copy = new Copy();
            	copy.setAvailable(true);
            	copy.setBook(book);
            	cr.save(copy);
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
    	if (authorMap.containsKey(authorName)) {
    		return authorMap.get(authorName);
    	}
    	
		Author author = new Author(authorName);
		try { author.setBirthYear((int) Float.parseFloat(authorBirthYear));} 
		catch (NumberFormatException e) {}
		author = ar.save(author);
		authorMap.put(authorName, author);
    	return author;
    }

    /* Returns the tag with a given name or creates it.
     * TODO: Maybe use tr instead of tagMap. */
    public Tag getTagByName(String tagName) {
    	if (tagMap.containsKey(tagName)) {
    		return tagMap.get(tagName);
    	}
    	
		Tag tag = tr.save(new Tag(tagName));
		tagMap.put(tagName, tag);
    	return tag;
    }
}
