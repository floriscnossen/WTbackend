package nl.workingtalent.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.workingtalent.backend.entity.Reservation;
import nl.workingtalent.backend.status.ReservationStatus;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByStatusOrderByStartDateDesc(ReservationStatus status);
	List<Reservation> findByUserIdOrderByStartDate(long id);
	List<Reservation> findByUserIdAndStatusOrderByStartDateDesc(long id, ReservationStatus status);
	List<Reservation> findByBookTitleOrderByStartDate(String title);
	List<Reservation> findByBookTitleLikeOrderByStartDate(String pattern);
	List<Reservation> findByUserFirstNameAndUserLastNameOrderByStartDate(String firstName, String lastName);
}
