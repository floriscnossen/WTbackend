package nl.workingtalent.backend;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
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

	@KafkaListener(id = "books", topics = "open_library_books")
	public void listen(String message) throws JsonProcessingException, IOException {

//		Make JSON parser object
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(message);

//		Parse author and save
		Author author = new Author();
		author.setName(String.valueOf(jsonNode.get("author")));
		ar.save(author);

//		Parse tags and save
		JsonNode tagNode = jsonNode.get("tag");
		List<Tag> tags = new ArrayList<Tag>();
		String tagName;
		for (int i = 0; i < tagNode.size(); i++) {
			tagName = tagNode.get(i).asText();
			Tag tag = new Tag(tagName);
			tr.save(tag);
			tags.add(tag);
		}

//		Parse books, add author and tags, then save

//		Book book = objectMapper.readValue(message, Book.class);
		Book book = new Book();
		book.setTitle(String.valueOf(jsonNode.get("title")));
		book.setAuthor(author);
		book.setTags(tags);
		br.save(book);
	}
}
