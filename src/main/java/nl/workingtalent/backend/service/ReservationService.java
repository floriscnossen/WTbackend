package nl.workingtalent.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.repository.ReservationRepository;

@Service
public class ReservationService {
	@Autowired    //dependency injection
	ReservationRepository rr;
    
    public List<Reservation> getReservations() {
		return rr.findAll();
	}
    
    public Optional<Reservation> getReservationById(long id) {
		return rr.findById(id);
	}
	
}
