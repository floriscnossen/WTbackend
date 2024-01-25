package nl.workingtalent.backend.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.repository.UserRepository;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Lees de token uit de request
		String authorizationToken = request.getHeader("Authorization");
		if (authorizationToken != null && !authorizationToken.isBlank()) {
			// Vind de user
			String token = authorizationToken.substring(7); // "Bearer absdfjsdhgfj76876kjhfkjh"

			Optional<User> userOptional = userRepository.findByToken(token);
			if (userOptional.isPresent()) {
				// Plaats die in de het request
				request.setAttribute("WT_USER", userOptional.get());
			}
		}
		
	}

}
