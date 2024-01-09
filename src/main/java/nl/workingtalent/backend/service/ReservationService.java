package nl.workingtalent.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.repository.ReservationRepository;

@Service
public class ReservationService {
	//Has a relatie
	@Autowired    //dependency injection
	ReservationRepository rr;
	
}
