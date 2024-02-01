package nl.workingtalent.backend.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import nl.workingtalent.backend.dto.ReservationDto;
import nl.workingtalent.backend.dto.ReservationSaveDto;
import nl.workingtalent.backend.dto.ResponseDto;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.mapper.DtoMapper;
import nl.workingtalent.backend.service.CopyService;
import nl.workingtalent.backend.service.ReservationService;
import nl.workingtalent.backend.status.ReservationStatus;

@RestController
@RequestMapping(path = "reservation")
@CrossOrigin
public class ReservationController {
	@Autowired
	ReservationService rs;

	@Autowired
	CopyService cs;
	
	@Autowired
	DtoMapper mapper;
	
	@GetMapping("all")
	public List<ReservationDto> getReservations(HttpServletRequest request) {
		User loggedInUser = (User) request.getAttribute("WT_USER");
		if (loggedInUser == null) {
			return null;
		}
		
		List<ReservationDto> reservations;
		if (loggedInUser.isAdmin()) {
			//return rs.getReservations().stream().map(mapper::toDto).collect(Collectors.toList());
			reservations = rs.getReservations()
		            .stream()
		            .map(mapper::toDto)
		            .sorted(Comparator.comparing(ReservationDto::getStartDate).reversed()) // Sort by startDate in descending order
		            .collect(Collectors.toList());
		} else {
			reservations = rs.getReservationsByUser(loggedInUser.getId())
		            .stream()
		            .map(mapper::toDto)
		            .sorted(Comparator.comparing(ReservationDto::getStartDate).reversed()) // Sort by startDate in descending order
		            .collect(Collectors.toList());
		}
		//return rs.getReservationsByUser(loggedInUser.getId()).stream().map(mapper::toDto).collect(Collectors.toList());
		return reservations;
	}
	
	@GetMapping("reserved")
	public List<ReservationDto> getReservationsReserved(HttpServletRequest request) {
		return getReservationsByStatus(request, ReservationStatus.RESERVED);
	}
	
	@GetMapping("loaned")
	public List<ReservationDto> getReservationsLoaned(HttpServletRequest request) {
		return getReservationsByStatus(request, ReservationStatus.LOANED);
	}
	
	@GetMapping("returned")
	public List<ReservationDto> getReservationsReturned(HttpServletRequest request) {
		return getReservationsByStatus(request, ReservationStatus.RETURNED);
	}
	
	public List<ReservationDto> getReservationsByStatus(HttpServletRequest request, ReservationStatus status) {
		User loggedInUser = (User) request.getAttribute("WT_USER");
		if (loggedInUser == null) {
			return null;
		}
		
		List<ReservationDto> reservations;
		if (loggedInUser.isAdmin()) {
			return rs.getReservationsByStatus(status).stream().map(mapper::toDto).collect(Collectors.toList());
		} 
		return rs.getReservationsByUserAndStatus(loggedInUser.getId(), status).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<ReservationDto> getReservationById(HttpServletRequest request, @PathVariable("id") long id) {
		User loggedInUser = (User) request.getAttribute("WT_USER");
		Optional<Reservation> optionalReservation = rs.getReservationById(id);
		if (loggedInUser == null || optionalReservation.isEmpty()) {
			return Optional.empty();
		}
		
		if (loggedInUser.isAdmin() || loggedInUser.getId() == optionalReservation.get().getUser().getId()) {
			return optionalReservation.map(mapper::toDto);
		}
		
		return Optional.empty();
	}
	
	@GetMapping("user/{id}")
	public List<ReservationDto> getReservationsByUser(HttpServletRequest request, @PathVariable("id") long id) {
		User loggedInUser = (User) request.getAttribute("WT_USER");
		if (loggedInUser == null) {
			return null;
		}
		
		if (loggedInUser.isAdmin() || loggedInUser.getId() == id) {
			return rs.getReservationsByUser(id).stream().map(mapper::toDto).collect(Collectors.toList());
		}

		return null;
	}
	
	@GetMapping("title/{title}")
	public List<ReservationDto> getReservationsByTitle(HttpServletRequest request, @PathVariable("title") String title) {
		System.out.println(title);
		User loggedInUser = (User) request.getAttribute("WT_USER");
		if (loggedInUser == null || !loggedInUser.isAdmin()) {
			return null;
		}
		
		return rs.getReservationsByTitle(title).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("name/{first}/{last}")
	public List<ReservationDto> getReservationsByName(HttpServletRequest request, @PathVariable("first") String firstName, @PathVariable("last") String lastName) {
		User loggedInUser = (User) request.getAttribute("WT_USER");
		if (loggedInUser == null || !loggedInUser.isAdmin()) {
			return null;
		}
		
		return rs.getReservationsByName(firstName, lastName).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseDto addReservation(HttpServletRequest request, @RequestBody ReservationSaveDto reservationDto) {
		User loggedInUser = (User) request.getAttribute("WT_USER");
		if (loggedInUser == null) {
			return new ResponseDto("Invalid login");
		}
		Reservation reservation = mapper.toEntity(reservationDto);
		if (reservation.getCopy() == null) {
			reservation.setStatus(ReservationStatus.RESERVED);
		}
		else {
			reservation.setStatus(ReservationStatus.LOANED);
			reservation.getCopy().setAvailable(false);
			cs.updateCopy(reservation.getCopy());
		}
		rs.addReservation(reservation);
		return new ResponseDto();
	}

	@PostMapping("email")
	public void addReservationByEmail(HttpServletRequest request, @RequestBody ReservationSaveDto reservationDto) {
		rs.addReservation(mapper.toEntity(reservationDto));
	}
	
	//Return the product
	@PutMapping("{id}")
	public ResponseDto updateReservation(HttpServletRequest request, @PathVariable("id") long id) {
		Optional<Reservation> optionalReservation = rs.getReservationById(id);
		Reservation reservation = optionalReservation.get();
		reservation.setId(id);
		reservation.setEndDate(LocalDate.now());
		reservation.getCopy().setAvailable(true);
		cs.updateCopy(reservation.getCopy());
		reservation.setStatus(ReservationStatus.RETURNED);
		rs.updateReservation(reservation);
		return new ResponseDto();
	}
	
	@PutMapping("{id}/{copyId}")
	public ResponseDto updateReservation(HttpServletRequest request, @PathVariable("id") long id, @PathVariable("copyId") long copyId) {
		Optional<Reservation> optionalReservation = rs.getReservationById(id);
		if (optionalReservation.isEmpty()) {
			return new ResponseDto("Invalid reservation id");
		}
		Reservation reservation = optionalReservation.get();
		Optional<Copy> optionalCopy = cs.getCopyById(copyId);
		if (optionalCopy.isEmpty()) {
			return new ResponseDto("Invalid copy id");
		}
		Copy copy = optionalCopy.get();
		if (copy.getBook().getId() != reservation.getBook().getId()) {
			return new ResponseDto("Copy is from wrong book");
		}
		copy.setAvailable(false);
		cs.updateCopy(copy);
		reservation.setCopy(copy);
		reservation.setStatus(ReservationStatus.LOANED);
		rs.updateReservation(reservation);
		return new ResponseDto();
	}
	
	@DeleteMapping("{id}")
	public void deleteReservation(HttpServletRequest request, @PathVariable("id") long id) {
		rs.deleteReservation(id);
	}
}
