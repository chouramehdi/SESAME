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

import com.st2i.sesame.entities.TypeDecoupage;
import com.st2i.sesame.entities.pojo.TypeDecoupagePojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.TypeDecoupageRepository;
import com.st2i.sesame.service.TypeDecoupageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TypeDecoupageContoller {
	@Autowired TypeDecoupageRepository typedecoupageRepo;
	@Autowired TypeDecoupageService typedecoupageservice;
	
	@PostMapping("/typedecoupage")
	public TypeDecoupage addTypeDecoupage(@Valid @RequestBody TypeDecoupage TypeDecoupage) {
		typedecoupageservice.addTypeDecoupage(TypeDecoupage);
		return TypeDecoupage;
	}
	
	@GetMapping("/typedecoupage")
	public Set<TypeDecoupagePojo> getTypeDecoupages() {
		List<TypeDecoupage> TypeDecoupageList = typedecoupageservice.getAll();
		Set<TypeDecoupagePojo> TypeDecoupages = new HashSet<>();
		TypeDecoupageList.forEach(TypeDecoupage ->{
			TypeDecoupagePojo _TypeDecoupage = new TypeDecoupagePojo();
			_TypeDecoupage.setId(TypeDecoupage.getId());
			_TypeDecoupage.setCode(TypeDecoupage.getCode());
			_TypeDecoupage.setDesignation(TypeDecoupage.getDesignation());
			TypeDecoupages.add(_TypeDecoupage);
		});
		return TypeDecoupages;
	}
	
	@GetMapping("/typedecoupage/{id}")
	public ResponseEntity<TypeDecoupagePojo> getTypeDecoupageById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		TypeDecoupage TypeDecoupage = typedecoupageRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TypeDecoupage not found for this id : " + id));
		TypeDecoupagePojo _TypeDecoupage = new TypeDecoupagePojo();
		_TypeDecoupage.setCode(TypeDecoupage.getCode());
		_TypeDecoupage.setId(TypeDecoupage.getId());
		_TypeDecoupage.setDesignation(TypeDecoupage.getDesignation());
		return ResponseEntity.ok().body(_TypeDecoupage);
	}
	
	@PutMapping("/typedecoupage")
	public ResponseEntity<String> updateTypeDecoupage( 
			@Valid @RequestBody TypeDecoupagePojo TypeDecoupageDetails) throws ResourceNotFoundException {
		TypeDecoupage TypeDecoupage = typedecoupageRepo.findById(TypeDecoupageDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Type not found for this id : " + TypeDecoupageDetails.getId()));
		
		TypeDecoupage.setCode(TypeDecoupageDetails.getCode());
		TypeDecoupage.setDesignation(TypeDecoupageDetails.getDesignation());
		typedecoupageRepo.save(TypeDecoupage);
		return ResponseEntity.ok().body("TypeDecoupage updated");
	}

	@DeleteMapping("/typedecoupage/{id}")
	public ResponseEntity<String> deleteTypeDecoupage(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		typedecoupageservice.delete(id);
		return ResponseEntity.ok().body("Type deleted");
	}
}
