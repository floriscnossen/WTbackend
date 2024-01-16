package nl.workingtalent.backend.repository;

import nl.workingtalent.backend.entity.Tag;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	Optional<Tag> findFirstByName(String name);
}
