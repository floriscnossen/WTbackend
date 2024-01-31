package nl.workingtalent.backend.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
    
    public User login(String email, String password) {
    	Optional<User> optionalUser = ur.findByEmail(email);
    	if (optionalUser.isEmpty()) {
    		return null;
    	}
    	User user = optionalUser.get();
		if (!BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
    		System.out.println(password + "Wrong password" + user.getPassword());
    		return null;
		}
		user.setToken(RandomStringUtils.random(100, true, true));
		user = ur.save(user);
		return user;
    }
}
