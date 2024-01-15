package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.UserDto;
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
}
