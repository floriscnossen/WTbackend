package nl.workingtalent.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.repository.CopyRepository;

@Service
public class CopyService {
	@Autowired    //dependency injection
	CopyRepository cr;
    
    public List<Copy> getCopies() {
		return cr.findAll();
	}
    
    public Optional<Copy> getCopyById(long id) {
		return cr.findById(id);
	}
	
}
