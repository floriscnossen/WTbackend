package nl.workingtalent.backend.controller;

import java.time.LocalDate;
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

import nl.workingtalent.backend.dto.ReservationDto;
import nl.workingtalent.backend.dto.ReservationSaveDto;
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
	public List<ReservationDto> getReservations() {
		return rs.getReservations().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<ReservationDto> getReservationById(@PathVariable("id") long id) {
		return rs.getReservationById(id).map(mapper::toDto);
	}
	
	@GetMapping("user/{id}")
	public List<ReservationDto> getReservationsByUser(@PathVariable("id") long id) {
		return rs.getReservationsByUser(id).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@PostMapping
	public void addReservation(@RequestBody ReservationSaveDto reservationDto) {
		rs.addReservation(mapper.toEntity(reservationDto));
	}
	
	@PutMapping("{id}")
	public void updateReservation(@PathVariable("id") long id, @RequestBody ReservationSaveDto reservationDto) {
		Reservation reservation = mapper.toEntity(reservationDto);
		reservation.setId(id);
		rs.updateReservation(reservation);
	}
	
	@DeleteMapping("{id}")
	public void deleteReservation(@PathVariable("id") long id) {
		rs.deleteReservation(id);
	}
}
