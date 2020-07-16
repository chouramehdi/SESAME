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

import com.st2i.sesame.entities.Devise;
import com.st2i.sesame.entities.pojo.DevisePojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.DeviseRepository;
import com.st2i.sesame.service.DeviseService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DeviseController {
	@Autowired DeviseRepository deviseRepo;
	@Autowired DeviseService deviseservice;
	
	@PostMapping("/devise")
	public Devise addDevise(@Valid @RequestBody Devise Devise) {
		deviseservice.addDevise(Devise);
		return Devise;
	}
	
	@GetMapping("/devise")
	public Set<DevisePojo> getDevises() {
		List<Devise> DeviseList = deviseservice.getAll();
		Set<DevisePojo> Devises = new HashSet<>();
		DeviseList.forEach(Devise ->{
			DevisePojo _Devise = new DevisePojo();
			_Devise.setId(Devise.getId());
			_Devise.setCode(Devise.getCode());
			_Devise.setDesignation(Devise.getDesignation());
			Devises.add(_Devise);
		});
		return Devises;
	}
	
	@GetMapping("/devise/{id}")
	public ResponseEntity<DevisePojo> getDeviseById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Devise Devise = deviseRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Devise not found for this id : " + id));
		DevisePojo _Devise = new DevisePojo();
		_Devise.setCode(Devise.getCode());
		_Devise.setId(Devise.getId());
		_Devise.setDesignation(Devise.getDesignation());
		return ResponseEntity.ok().body(_Devise);
	}
	
	@PutMapping("/devise")
	public ResponseEntity<String> updateDevise( 
			@Valid @RequestBody DevisePojo DeviseDetails) throws ResourceNotFoundException {
		Devise Devise = deviseRepo.findById(DeviseDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Devise not found for this id : " + DeviseDetails.getId()));
		
		Devise.setCode(DeviseDetails.getCode());
		Devise.setDesignation(DeviseDetails.getDesignation());
		Devise.setTaux(DeviseDetails.getTaux());
		deviseRepo.save(Devise);
		return ResponseEntity.ok().body("Devise updated");
	}

	@DeleteMapping("/devise/{id}")
	public ResponseEntity<String> deleteDevise(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		deviseservice.delete(id);
		return ResponseEntity.ok().body("Devise deleted");
	}
}
