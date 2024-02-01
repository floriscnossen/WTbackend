package nl.workingtalent.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.repository.CopyRepository;
import nl.workingtalent.backend.repository.ReservationRepository;
import nl.workingtalent.backend.status.ReservationStatus;

@Service
public class ReservationService {
	@Autowired
	ReservationRepository rr;

	@Autowired
	CopyRepository cr;
	
    public List<Reservation> getReservations() {
		return rr.findAll();
	}
    
    public List<Reservation> getReservationsByStatus(ReservationStatus status) {
		return rr.findByStatusOrderByStartDateDesc(status);
	}
    
    public Optional<Reservation> getReservationById(long id) {
		return rr.findById(id);
	}
    
    
    public List<Reservation> getReservationsByUser(long id) {
		return rr.findByUserIdOrderByStartDate(id);
	}
    
    public List<Reservation> getReservationsByUserAndStatus(long id, ReservationStatus status) {
		return rr.findByUserIdAndStatusOrderByStartDateDesc(id, status);
	}
    
    
    public List<Reservation> getReservationsByTitle(String title) {
		return rr.findByBookTitleLikeOrderByStartDate("%" + title + "%");
	}
    
    public List<Reservation> getReservationsByName(String firstName, String lastName) {
		return rr.findByUserFirstNameAndUserLastNameOrderByStartDate("%" + firstName + "%", "%" + lastName + "%");
	}
    
    public Reservation addReservation(Reservation reservation) {
    	if (reservation.getStatus() == ReservationStatus.LOANED) {
    		if (reservation.getCopy() == null || !reservation.getCopy().isAvailable()) {
    			return null;
    		}
    		reservation.getCopy().setAvailable(false);
    		reservation.setCopy(cr.save(reservation.getCopy()));
    		
    	}
    	return rr.save(reservation);
    }
    
    public Reservation updateReservation(Reservation reservation) {
    	if (reservation.getStatus() == ReservationStatus.LOANED) {
    		if (reservation.getCopy() == null || !reservation.getCopy().isAvailable()) {
    			return null;
    		}
    		reservation.getCopy().setAvailable(false);
    		reservation.setCopy(cr.save(reservation.getCopy()));
    	}
    	else if (reservation.getStatus() == ReservationStatus.RETURNED) {
    		if (reservation.getCopy() == null) {
    			return null;
    		}
    		reservation.getCopy().setAvailable(true);
    		reservation.setCopy(cr.save(reservation.getCopy()));
    	}
    	return rr.save(reservation);
    }
    
    public void deleteReservation(long id) {
    	rr.deleteById(id);
    }
	
}
