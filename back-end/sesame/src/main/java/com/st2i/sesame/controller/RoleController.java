package com.st2i.sesame.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.st2i.sesame.entities.pojo.RolePojo;
import com.st2i.sesame.entities.User;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.RoleRepository;
import com.st2i.sesame.repository.UserRepository;
import com.st2i.sesame.service.RoleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RoleController {
	@Autowired
	RoleService roleService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserRepository userRepo;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/role")
	public Role addrole(@Valid @RequestBody Role role) {
		roleService.addRole(role);
		return role;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/role")
	public Set<RolePojo> getroles() {
		List<Role> rolesList = roleRepo.findAll();
		Set<RolePojo> roles = new HashSet<>();
		rolesList.forEach(role -> {
			RolePojo _role = new RolePojo();
			_role.setId(role.getId());
			_role.setName(role.getName());
			roles.add(_role);
		});
		return roles;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/role/{id}")
	public ResponseEntity<RolePojo> getroleById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Role role = roleRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id : " + id));
		RolePojo _role = new RolePojo();
		_role.setName(role.getName());
		_role.setId(role.getId());
		return ResponseEntity.ok().body(_role);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/role/{id}")
	public ResponseEntity<String> updateRole(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody RolePojo roleDetails) throws ResourceNotFoundException {
		Role role = roleRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id : " + id));
		
		role.setName(roleDetails.getName());
		roleRepo.save(role);
		return ResponseEntity.ok().body("Role updated");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/role/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Role role = roleRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + id));
		Set<User> userList = new HashSet<>();
		userList = role.getUsers();
		if(!userList.isEmpty()) {
		userRepo.deleteAll(userList);
		}
		roleRepo.delete(role);
		return ResponseEntity.ok().body("Role deleted");
	}
}
