package nl.workingtalent.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.workingtalent.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	@Query("SELECT u FROM User u WHERE u.name =?1")
	Optional<User> findByEmail(String email);
	
	Optional<User> findByToken(String token);
	
}
