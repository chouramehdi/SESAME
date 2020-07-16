package com.st2i.sesame.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st2i.sesame.entities.TypeIndicateur;
import com.st2i.sesame.entities.pojo.TypeIndicateurPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.TypeIndicateurRepository;
import com.st2i.sesame.service.TypeIndicateurService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TypeIndicateurController {
	@Autowired TypeIndicateurRepository typeIndicateurRepo;
	@Autowired TypeIndicateurService typeIndicateurservice;
	
	@PostMapping("/typeindicateur")
	public TypeIndicateur addTypeIndicateur(@Valid @RequestBody TypeIndicateur TypeIndicateur) {
		typeIndicateurservice.addTypeIndicateur(TypeIndicateur);
		return TypeIndicateur;
	}
	
	@GetMapping("/typeindicateur")
	public Set<TypeIndicateurPojo> getTypeIndicateurs() {
		List<TypeIndicateur> TypeIndicateurList = typeIndicateurservice.getAll();
		Set<TypeIndicateurPojo> TypeIndicateurs = new HashSet<>();
		TypeIndicateurList.forEach(TypeIndicateur ->{
			TypeIndicateurPojo _TypeIndicateur = new TypeIndicateurPojo();
			_TypeIndicateur.setId(TypeIndicateur.getId());
			_TypeIndicateur.setCode(TypeIndicateur.getCode());
			_TypeIndicateur.setDesignation(TypeIndicateur.getDesignation());
			TypeIndicateurs.add(_TypeIndicateur);
		});
		return TypeIndicateurs;
	}
	
	@GetMapping("/typeindicateur/{id}")
	public ResponseEntity<TypeIndicateurPojo> getTypeIndicateurById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		TypeIndicateur TypeIndicateur = typeIndicateurRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TypeIndicateur not found for this id : " + id));
		TypeIndicateurPojo _TypeIndicateur = new TypeIndicateurPojo();
		_TypeIndicateur.setCode(TypeIndicateur.getCode());
		_TypeIndicateur.setId(TypeIndicateur.getId());
		_TypeIndicateur.setDesignation(TypeIndicateur.getDesignation());
		return ResponseEntity.ok().body(_TypeIndicateur);
	}
	
	@PutMapping("/typeindicateur")
	public ResponseEntity<String> updateTypeIndicateur( 
			@Valid @RequestBody TypeIndicateurPojo TypeIndicateurDetails) throws ResourceNotFoundException {
		TypeIndicateur TypeIndicateur = typeIndicateurRepo.findById(TypeIndicateurDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Type not found for this id : " + TypeIndicateurDetails.getId()));
		
		TypeIndicateur.setCode(TypeIndicateurDetails.getCode());
		TypeIndicateur.setDesignation(TypeIndicateurDetails.getDesignation());
		typeIndicateurRepo.save(TypeIndicateur);
		return ResponseEntity.ok().body("TypeIndicateur updated");
	}

	@DeleteMapping("/typeindicateur/{id}")
	public ResponseEntity<String> deleteTypeIndicateur(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		typeIndicateurservice.delete(id);
		return ResponseEntity.ok().body("Type deleted");
	}
}
