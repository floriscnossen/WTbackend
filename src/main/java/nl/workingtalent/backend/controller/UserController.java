package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.service.UserService;

@RestController
@RequestMapping(path = "user")
public class UserController {
	@Autowired
	UserService us;
	
	@GetMapping("all")
	public List<User> getUsers() {
		return us.getUsers();
	}
	
	@GetMapping("{id}")
	public Optional<User> getUserById(@PathVariable("id") long id) {
		return us.getUserById(id);
	}
}
