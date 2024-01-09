package nl.workingtalent.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.service.ReservationService;

@RestController
@RequestMapping(path = "reservation")
public class ReservationController {
	@Autowired
	ReservationService rs;

}
