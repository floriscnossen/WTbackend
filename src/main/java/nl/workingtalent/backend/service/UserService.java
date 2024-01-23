package nl.workingtalent.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.repository.UserRepository;

@Service
public class UserService {
	@Autowired    //dependency injection
	UserRepository ur;
    
    public List<User> getUsers() {
		return ur.findAll();
	}
    
    public Optional<User> getUserById(long id) {
		return ur.findById(id);
	}
    
    public Optional<User> getUserByEmail(String email) {
		return ur.findByEmail(email);
	}
    
    public User addUser(User user) {
    	return ur.save(user);
    }
    
    public User updateUser(User user) {
    	return ur.save(user);
    }
    
    public void deleteUser(long id) {
    	ur.deleteById(id);
    }
}
