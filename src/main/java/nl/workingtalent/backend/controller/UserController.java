package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.UserDto;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.service.UserService;

@RestController
@RequestMapping(path = "user")
public class UserController {
	@Autowired
	UserService us;
	
	@GetMapping("all")
	public List<UserDto> getUsers() {
		return us.getUsers().stream().map(u -> u.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<UserDto> getUserById(@PathVariable("id") long id) {
		return us.getUserById(id).map(u -> u.toDto());
	}
	
	@PostMapping
	public void addUser(@RequestBody User a) {
		us.addUser(a);
	}
	
	@PutMapping("{id}")
	public void updateUser(@PathVariable("id") long id, @RequestBody User a) {
		a.setId(id);
		us.updateUser(a);
	}
	
	@DeleteMapping("{id}")
	public void updateUser(@PathVariable("id") long id) {
		us.deleteUser(id);
	}
}
