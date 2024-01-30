package nl.workingtalent.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.entity.User;
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
    
    public List<Reservation> getReservationsByUser(long id) {
		return rr.findByUserIdOrderByStartDate(id);
	}
    
    public List<Reservation> getReservationsByTitle(String title) {
		return rr.findByBookTitleLikeOrderByStartDate("%" + title + "%");
	}
    
    public List<Reservation> getReservationsByName(String firstName, String lastName) {
		return rr.findByUserFirstNameAndUserLastNameOrderByStartDate(firstName, lastName);
	}
    
    public Reservation addReservation(Reservation reservation) {
    	return rr.save(reservation);
    }
    
    public Reservation updateReservation(Reservation reservation) {
    	return rr.save(reservation);
    }
    
    public void deleteReservation(long id) {
    	rr.deleteById(id);
    }
	
}
