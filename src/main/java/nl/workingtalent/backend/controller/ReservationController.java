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
import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.mapper.DtoMapper;
import nl.workingtalent.backend.service.ReservationService;

@RestController
@RequestMapping(path = "reservation")
@CrossOrigin
public class ReservationController {
	@Autowired
	ReservationService rs;
	
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
		rs.addReservation(mapper.toEntity(reservationDto));
		return new ResponseDto();
	}

	@PostMapping("email")
	public void addReservationByEmail(HttpServletRequest request, @RequestBody ReservationSaveDto reservationDto) {
		rs.addReservation(mapper.toEntity(reservationDto));
	}
	
	@PutMapping("{id}")
	public void updateReservation(HttpServletRequest request, @PathVariable("id") long id, @RequestBody ReservationSaveDto reservationDto) {
		Reservation reservation = mapper.toEntity(reservationDto);
		reservation.setId(id);
		rs.updateReservation(reservation);
	}
	
	//TODO
//	@PutMapping("{id}/{copyId}")
//	public void updateReservation(HttpServletRequest request, @PathVariable("id") long id, @PathVariable("copyId") long copyId, @RequestBody ReservationSaveDto reservationDto) {
//		Reservation reservation = mapper.toEntity(reservationDto);
//		reservation.setId(id);
//		rs.updateReservation(reservation);
//	}
	
	@DeleteMapping("{id}")
	public void deleteReservation(HttpServletRequest request, @PathVariable("id") long id) {
		rs.deleteReservation(id);
	}
}
