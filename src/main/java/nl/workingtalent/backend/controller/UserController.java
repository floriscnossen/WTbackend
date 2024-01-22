package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import nl.workingtalent.backend.dto.UserDto;
import nl.workingtalent.backend.dto.UserSaveDto;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.mapper.DtoMapper;
import nl.workingtalent.backend.service.UserService;

@RestController
@RequestMapping(path = "user")
@CrossOrigin
public class UserController {
	@Autowired
	UserService us;
	
	@Autowired
	DtoMapper mapper;
	
	@GetMapping("all")
	public List<UserDto> getUsers() {
		return us.getUsers().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<UserDto> getUserById(@PathVariable("id") long id) {
		return us.getUserById(id).map(mapper::toDto);
	}
	
	@PostMapping
	public void addUser(@RequestBody UserSaveDto userDto) {
		us.addUser(mapper.toEntity(userDto));
	}
	
	@PutMapping("{id}")
	public void updateUser(@PathVariable("id") long id, @RequestBody UserSaveDto userDto) {
		User user = mapper.toEntity(userDto);
		user.setId(id);
		us.updateUser(user);
	}
	
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") long id) {
		us.deleteUser(id);
	}
}
