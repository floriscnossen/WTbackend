package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.ReservationDto;
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
	public Optional<ReservationDto> getAuthorById(@PathVariable("id") long id) {
		return rs.getReservationById(id).map(r -> r.toDto());
	}
}
