package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
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

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import nl.workingtalent.backend.dto.LoginRequestDto;
import nl.workingtalent.backend.dto.LoginResponseDto;
import nl.workingtalent.backend.dto.ResponseDto;
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
	public ResponseDto updateUser(HttpServletRequest request, @PathVariable("id") long id, @RequestBody UserSaveDto userDto) {
		User loggedInUser = (User)request.getAttribute("WT_USER");
		if (loggedInUser != null) {
			// Admin - LoggedInUser gelijk is aan de id
			if (loggedInUser.isAdmin() || loggedInUser.getId() == id) {
				User user = mapper.toEntity(userDto);
				user.setId(id);
				us.updateUser(user);

				return new ResponseDto(true, null);
			}
		}

		return new ResponseDto(false, "Authenticatie error");
	}
	
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") long id) {
		us.deleteUser(id);
	}
	
	@PostMapping("login")
	public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
		User user = us.login(dto.getEmail(), dto.getPassword());

		if (user != null) {
			return new LoginResponseDto(user.getToken(), user.isAdmin(), user.getFirstName() + " " + user.getLastName());
		} else {
			return new LoginResponseDto();
		}
	}
}
