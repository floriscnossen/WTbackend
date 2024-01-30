package nl.workingtalent.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.entity.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByUserIdOrderByStartDate(long id);
	List<Reservation> findByBookTitleOrderByStartDate(String title);
	List<Reservation> findByUserFirstNameAndUserLastNameOrderByStartDate(String firstName, String lastName);
}
