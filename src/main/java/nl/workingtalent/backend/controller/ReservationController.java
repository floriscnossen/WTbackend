package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.service.ReservationService;

@RestController
@RequestMapping(path = "reservation")
public class ReservationController {
	@Autowired
	ReservationService rs;
	
	@GetMapping("all")
	public List<Reservation> getReservations() {
		return rs.getReservations();
	}
	
	@GetMapping("{id}")
	public Optional<Reservation> getAuthorById(@PathVariable("id") long id) {
		return rs.getReservationById(id);
	}
}
