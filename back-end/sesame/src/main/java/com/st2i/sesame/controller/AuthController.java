package com.st2i.sesame.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st2i.sesame.security.jwt.*;
import com.st2i.sesame.payload.*;
import com.st2i.sesame.repository.*;
import com.st2i.sesame.security.services.*;
import com.st2i.sesame.service.AuthEventLogService;
import com.st2i.sesame.entities.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthEventLogService authEventLogService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			AuthEventLogHistory event = new AuthEventLogHistory();
			event.setDate(LocalDateTime.now());
			event.setUsername(loginRequest.getUsername());
			event.setStatus("Accepté");
			event.setEventMessage("Login event");
			authEventLogService.addEvent(event);
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));

		} catch (Exception e) {
			AuthEventLogHistory event = new AuthEventLogHistory();
			event.setDate(LocalDateTime.now());
			event.setUsername(loginRequest.getUsername());
			event.setStatus("Rejeté");
			event.setEventMessage("Login event");
			authEventLogService.addEvent(event);
			return ResponseEntity.badRequest().body(null);
		}
		
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
		Set<Role> roles = new HashSet<>();
		Set<Integer> optionalRoleId = new HashSet<>();
		signUpRequest.getRole().forEach(role -> {
			optionalRoleId.add(role);
			Optional<Role> optionalRole = roleRepository.findById(role);
			if (optionalRole.isPresent()) {
				roles.add(optionalRole.get());
			}
		});
		user.setRoles(roles);
		//user.setRoles(optionalRoleId);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}