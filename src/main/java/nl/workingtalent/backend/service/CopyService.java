package nl.workingtalent.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.repository.CopyRepository;

@Service
public class CopyService {
	//Has a relatie
	@Autowired    //dependency injection
	CopyRepository cr;
	
}
