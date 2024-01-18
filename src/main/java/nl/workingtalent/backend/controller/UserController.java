package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.LoginRequestDto;
import nl.workingtalent.backend.dto.LoginResponseDto;
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
	
	@PostMapping("authenticate")
	public LoginResponseDto authenticate(@RequestBody LoginRequestDto dto) {
		Optional<User> optionalUser = us.findByEmail(dto.getEmail());
		if (optionalUser.isEmpty()) {
			return new LoginResponseDto(false, null);
		}
		
		User user = optionalUser.get();
		if (!user.getPassword().equals(dto.getPassword())) {
			return new LoginResponseDto(false, null);
		}
		
		// Generate new token
		user.setToken(RandomStringUtils.random(100, true, true));

		// Save the user to save the token
		us.save(user);

		return new LoginResponseDto(true, user.getToken());
	}

}

