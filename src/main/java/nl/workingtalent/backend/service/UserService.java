package nl.workingtalent.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.repository.UserRepository;

@Service
public class UserService {
	//Has a relatie
	@Autowired    //dependency injection
	UserRepository ur;

}
