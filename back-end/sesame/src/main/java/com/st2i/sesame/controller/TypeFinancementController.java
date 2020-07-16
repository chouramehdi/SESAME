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

import com.st2i.sesame.entities.TypeFinancement;
import com.st2i.sesame.entities.pojo.TypeFinancementPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.TypeFinancementRepository;
import com.st2i.sesame.service.TypeFinancementService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TypeFinancementController {
	
	@Autowired TypeFinancementRepository typefinanceRepo;
	@Autowired TypeFinancementService typefinanceservice;
	
	@PostMapping("/typefinance")
	public TypeFinancement addTypeFinancement(@Valid @RequestBody TypeFinancement TypeFinancement) {
		typefinanceservice.addTypeFinancement(TypeFinancement);
		return TypeFinancement;
	}
	
	@GetMapping("/typefinance")
	public Set<TypeFinancementPojo> getTypeFinancements() {
		List<TypeFinancement> TypeFinancementList = typefinanceservice.getAll();
		Set<TypeFinancementPojo> TypeFinancements = new HashSet<>();
		TypeFinancementList.forEach(TypeFinancement ->{
			TypeFinancementPojo _TypeFinancement = new TypeFinancementPojo();
			_TypeFinancement.setId(TypeFinancement.getId());
			_TypeFinancement.setCode(TypeFinancement.getCode());
			_TypeFinancement.setDesignation(TypeFinancement.getDesignation());
			TypeFinancements.add(_TypeFinancement);
		});
		return TypeFinancements;
	}
	
	@GetMapping("/typefinance/{id}")
	public ResponseEntity<TypeFinancementPojo> getTypeFinancementById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		TypeFinancement TypeFinancement = typefinanceRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TypeFinancement not found for this id : " + id));
		TypeFinancementPojo _TypeFinancement = new TypeFinancementPojo();
		_TypeFinancement.setCode(TypeFinancement.getCode());
		_TypeFinancement.setId(TypeFinancement.getId());
		_TypeFinancement.setDesignation(TypeFinancement.getDesignation());
		return ResponseEntity.ok().body(_TypeFinancement);
	}
	
	@PutMapping("/typefinance")
	public ResponseEntity<String> updateTypeFinancement( 
			@Valid @RequestBody TypeFinancementPojo TypeFinancementDetails) throws ResourceNotFoundException {
		TypeFinancement TypeFinancement = typefinanceRepo.findById(TypeFinancementDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Type not found for this id : " + TypeFinancementDetails.getId()));
		
		TypeFinancement.setCode(TypeFinancementDetails.getCode());
		TypeFinancement.setDesignation(TypeFinancementDetails.getDesignation());
		typefinanceRepo.save(TypeFinancement);
		return ResponseEntity.ok().body("TypeFinancement updated");
	}

	@DeleteMapping("/typefinance/{id}")
	public ResponseEntity<String> deleteTypeFinancement(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		typefinanceservice.delete(id);
		return ResponseEntity.ok().body("Type deleted");
	}
	
	@GetMapping("/typefinance/{designation}")
	public TypeFinancement getBailleurByDesignation(@PathVariable(value = "designation") String designation){
		TypeFinancement  type =typefinanceservice.findByDesignation(designation);
		return type;
	}
}
