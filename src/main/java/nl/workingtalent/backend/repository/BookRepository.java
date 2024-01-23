package nl.workingtalent.backend.repository;

import nl.workingtalent.backend.entity.Author;
import nl.workingtalent.backend.entity.Book;

import java.util.List;

import javax.swing.text.html.HTML.Tag;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	List<Book> findByTitleContaining(String infix);
	
	@Query("SELECT b FROM Book b WHERE b.title LIKE ?1")
	List<Book> findByTitleLikeSorted(String likePattern, Sort sort);
	
	List<Book> findByTagsId(long tagId);
	
	List<Book> findByTagsName(String tagName);
	
	List<Book> findByAuthor(Author author);
}