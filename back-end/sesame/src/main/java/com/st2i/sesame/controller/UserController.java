package com.st2i.sesame.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st2i.sesame.entities.Role;
import com.st2i.sesame.entities.User;
import com.st2i.sesame.entities.pojo.UserListPojo;
import com.st2i.sesame.entities.pojo.UserPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.RoleRepository;
import com.st2i.sesame.repository.UserRepository;
import com.st2i.sesame.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/user")
	public User addUser(@Valid @RequestBody User user) {
		userService.addUser(user);
		return user;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id : " + id));
		return ResponseEntity.ok().body(user);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody UserPojo userPojo) throws ResourceNotFoundException {
		Optional<User> optionalUser = userRepo.findById(userPojo.getId());
		if (!optionalUser.isPresent()) {
			throw new ResourceNotFoundException("user not found for this id : " + userPojo.getId());
		}
		User user = optionalUser.get();
		Set<Role> roles = new HashSet<>();
		user.setUsername(userPojo.getUsername());
		if (userPojo.getPassword() != null) {
			user.setPassword(encoder.encode(userPojo.getPassword()));
		}
		userPojo.getRolesId().forEach(roleId -> {
			Optional<Role> optionalRole = roleRepo.findById(roleId);
			if (optionalRole.isPresent()) {
				roles.add(optionalRole.get());
			}
		});
		user.setRoles(roles);
		final User updatedUser = userRepo.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		Set<Role> r = new HashSet<>();
		user.setRoles(r);
		userRepo.delete(user);
		return ResponseEntity.ok().body(null);

	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/user")
	public Set<UserListPojo> GetListUser(){
		List<User> usersList = userRepo.findAll();
		Set<UserListPojo> users = new HashSet<>();
		usersList.forEach(user->{
			UserListPojo _user = new UserListPojo();
			_user.setId(user.getId());
			_user.setUsername(user.getUsername());
			Set<String> role = new HashSet<>();
			user.getRoles().forEach(element->{
				role.add(element.getName());
			});
			_user.setRoles(role);
			users.add(_user);
		});
		
		return users;
	}
	
}
