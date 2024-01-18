package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.ReservationDto;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.service.ReservationService;

@RestController
@RequestMapping(path = "reservation")
public class ReservationController {
	@Autowired
	ReservationService rs;
	
	@GetMapping("all")
	public List<ReservationDto> getReservations() {
		return rs.getReservations().stream().map(r -> r.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<ReservationDto> getReservationById(@PathVariable("id") long id) {
		return rs.getReservationById(id).map(r -> r.toDto());
	}
	
	@GetMapping("user/{id}")
	public List<ReservationDto> getReservationsByUser(@PathVariable("id") long id) {
		return rs.getReservationsByUser(id).stream().map(r -> r.toDto()).collect(Collectors.toList());
	}
	
	@PostMapping
	public void addReservation(@RequestBody Reservation a) {
		rs.addReservation(a);
	}
	
	@PutMapping("{id}")
	public void updateReservation(@PathVariable("id") long id, @RequestBody Reservation a) {
		a.setId(id);
		rs.updateReservation(a);
	}
	
	@DeleteMapping("{id}")
	public void deleteReservation(@PathVariable("id") long id) {
		rs.deleteReservation(id);
	}
}
