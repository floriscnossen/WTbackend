package nl.workingtalent.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.repository.AuthorRepository;
import nl.workingtalent.backend.repository.BookRepository;
import nl.workingtalent.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Autowired
	BookRepository br;

	@Autowired
	AuthorRepository ar;

	@Autowired
	TagRepository tr;

	@KafkaListener(id = "books", topicPartitions = {
			@TopicPartition(topic= "open_library_books", partitions = "0") })
	public void listen(String message) throws JsonProcessingException, IOException {

		System.out.println(message);

//		Make JSON parser object
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(message);

//		Check if ISBN number is already in database and exit if it is.
		if (br.findByIsbnNumber(String.valueOf(jsonNode.get("isbnNumber"))).size() > 0) {
			return;
		}


//		Parse author and save
		Author author = new Author();
		author.setName(String.valueOf(jsonNode.get("author")));
		ar.save(author);
//		if (ar.findByName(author.getName()).size() == 0){
//			System.out.println(author.getName() + "not found, saving!");
//			ar.save(author);
//		}

//		Parse tags and save
		JsonNode tagNode = jsonNode.get("tag");
		List<Tag> tags = new ArrayList<Tag>();
		String tagName;
		for (int i = 0; i < tagNode.size(); i++) {
			tagName = tagNode.get(i).asText();
			Tag tag = new Tag(tagName);

//			if (tr.findByName(tag.getName()).size() == 0 ) {
//				tr.save(tag);
//			}
			tr.save(tag);
			tags.add(tag);
		}

//		Parse books, add author and tags, then save
		Book book = new Book();
		book.setTitle(String.valueOf(jsonNode.get("title")));
		book.setDescription(String.valueOf(jsonNode.get("description")));

		
		try {book.setReleaseDate(LocalDate.parse(String.valueOf(jsonNode.get("releaseDate")).replaceAll("\"", "")));
        }
		catch (DateTimeParseException e) {System.out.println(e.toString());}
//		book.setReleaseDate(LocalDate.parse(String.valueOf(jsonNode.get("releaseDate")).replaceAll("\"", "")));
		book.setIsbnNumber(String.valueOf(jsonNode.get("isbnNumber")));
		book.setPublisher(String.valueOf(jsonNode.get("publisher")));
		try { book.setPageCount((int)
				Float.parseFloat(String.valueOf(jsonNode.get("pageCount"))));}
		catch (NumberFormatException f) {System.out.println(f.toString());}
//		book.setPageCount(Integer.parseInt(String.valueOf(jsonNode.get("pageCount"))));
		book.setImageUrl(String.valueOf(jsonNode.get("imageUrl")));
		book.setSource(String.valueOf(jsonNode.get("source")));
		book.setAuthor(author);
		book.setTags(tags);

		br.save(book);
	}

}
