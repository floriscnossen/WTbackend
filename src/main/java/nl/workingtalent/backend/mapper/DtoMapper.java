package nl.workingtalent.backend.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.workingtalent.backend.dto.*;
import nl.workingtalent.backend.service.*;
import nl.workingtalent.backend.entity.*;

@Component
public class DtoMapper {
	@Autowired
	AuthorService as;

	@Autowired
	BookService bs;

	@Autowired
	CopyService cs;

	@Autowired
	ReservationService rs;

	@Autowired
	TagService ts;

	@Autowired
	UserService us;
	
	public AuthorDto toDto(Author author) {
		if (author == null) return null;
		AuthorDto a = new AuthorDto(author.getId(), author.getName(), author.getBirthYear(), author.getNationality());
		if (author.getBooks() == null) {
			a.setBooks(new ArrayList<Long>());
		}
		else {
			a.setBooks(author.getBooks().stream().map(b -> b.getId()).collect(Collectors.toList()));
		}
		return a;
	}
	
	public Author toEntity(AuthorSaveDto a) {
		if (a == null) return null;
		return new Author(a.getName(), a.getBirthYear(), a.getNationality());
	}
	
	public BookDto toDto(Book book) {
		if (book == null) return null;
		BookDto b = new BookDto(book.getId(), book.getTitle(), toDto(book.getAuthor()), book.getDescription(), book.getReleaseDate(), 
				book.getIsbnNumber(), book.getPublisher(), book.getPageCount(), book.getRelatedCourses(), book.getFormat(), book.getInfo(), 
				book.getRating(), book.getEdition(), book.getImageUrl());
		if (book.getTags() == null) {
			b.setTags(new ArrayList<String>());
		}
		else {
			b.setTags(book.getTags().stream().map(t -> t.getName()).collect(Collectors.toList()));
		}
		if (book.getCopies() == null) {
			b.setCopies(new ArrayList<Long>());
		}
		else {
			b.setCopies(book.getCopies().stream().map(c -> c.getId()).collect(Collectors.toList()));
		}
		for (Copy copy : book.getCopies()) {
			if (copy.isAvailable()) {
				b.setAvailable(true);
			}
		}
		return b;
	}
	
	/* TODO: Book */
	public Book toEntity(BookSaveDto b) {
		if (b == null) return null;
		return new Book();
	}
	
	public CopyDto toDto(Copy copy) {
		if (copy == null) return null;
		CopyDto c = new CopyDto(copy.getId(),toDto(copy.getBook()), copy.isAvailable());
		//TODO: Misschien de reservations doorsturen van de huidige user of alle users voor admins?
		return c;
	}
	
	public Copy toEntity(CopySaveDto c) {
		if (c == null) return null;
		Optional<Book> optionalBook = bs.getBookById(c.getBookId());
		if (optionalBook.isEmpty()) {
			return null;
		}
		return new Copy(optionalBook.get(), c.isAvailable());
	}
	
	public ReservationDto toDto(Reservation reservation) {
		if (reservation == null) return null;
		return new ReservationDto(reservation.getId(),
				toDto(reservation.getCopy()),
				toDto(reservation.getBook()),
				toDto(reservation.getUser()), 
				reservation.getStartDate(),
				reservation.getEndDate(),
				reservation.getStatus());
	}
	
	public Reservation toEntity(ReservationSaveDto r) {
		if (r == null) return null;
		Optional<Copy> optionalCopy = cs.getCopyById(r.getCopyId());
		Optional<User> optionalUser = us.getUserById(r.getUserId());
		Optional<Book> optionalBook = bs.getBookById(r.getBookId());
		if (optionalUser.isEmpty()) {
			return null;
		}
		LocalDate startDate = r.getStartDate();
		if (startDate == null) startDate = LocalDate.now();
		
		Reservation reservation = new Reservation(optionalUser.get(),startDate, r.getEndDate(),r.getStatus());
		if (!optionalBook.isEmpty()) {
			reservation.setBook(optionalBook.get());
		}
		if (!optionalCopy.isEmpty()) {
			reservation.setCopy(optionalCopy.get());
		}
		return reservation;
//		return new Reservation(optionalCopy.get(),optionalUser.get(),r.getStartDate(), r.getEndDate(),r.getStatus());
	}
	
	public TagDto toDto(Tag tag) {
		if (tag == null) return null;
		TagDto t = new TagDto(tag.getId(),tag.getName());
		if (tag.getBooks() == null) {
			t.setBooks(new ArrayList<Long>());
		}
		else {
			t.setBooks(tag.getBooks().stream().map(b -> b.getId()).collect(Collectors.toList()));
		}
		return t;
	}
	
	public Tag toEntity(TagSaveDto t) {
		if (t == null) return null;
		return new Tag(t.getName());
	}
	
	public UserDto toDto(User user) {
		if (user == null) return null;
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isAdmin());
	}
	
	public User toEntity(UserSaveDto u) {
		if (u == null) return null;
		return new User(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword(), u.isAdmin());
	}
}
