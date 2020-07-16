package com.st2i.sesame.controller;

import java.util.List;

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

import com.st2i.sesame.entities.ResponsableIndicateur;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.ResponsableIndicateurRepository;
import com.st2i.sesame.service.ResponsableIndicateurService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ResponsableIndicateurController {

	@Autowired ResponsableIndicateurRepository ResponsableIndicateurRepo;
	@Autowired ResponsableIndicateurService ResponsableIndicateurservice;
	
	@PostMapping("/responsable")
	public ResponsableIndicateur addResponsableIndicateur(@Valid @RequestBody ResponsableIndicateur ResponsableIndicateur) {
		ResponsableIndicateurservice.addResponsableIndicateur(ResponsableIndicateur);
		return ResponsableIndicateur;
	}
	
	@GetMapping("/responsable")
	public List<ResponsableIndicateur> getResponsableIndicateurs() {
		List<ResponsableIndicateur> ResponsableIndicateurList = ResponsableIndicateurservice.getAll();
		return ResponsableIndicateurList;
	}
	
	@GetMapping("/responsable/{id}")
	public ResponseEntity<ResponsableIndicateur> getResponsableIndicateurById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		ResponsableIndicateur ResponsableIndicateur = ResponsableIndicateurRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ResponsableIndicateur not found for this id : " + id));

		return ResponseEntity.ok().body(ResponsableIndicateur);
	}
	
	@PutMapping("/responsable")
	public ResponseEntity<String> updateResponsableIndicateur( 
			@Valid @RequestBody ResponsableIndicateur ResponsableIndicateurDetails) throws ResourceNotFoundException {
		ResponsableIndicateur ResponsableIndicateur = ResponsableIndicateurRepo.findById(ResponsableIndicateurDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Type not found for this id : " + ResponsableIndicateurDetails.getId()));
		
		ResponsableIndicateur.setCode(ResponsableIndicateurDetails.getCode());
		ResponsableIndicateur.setDesignation(ResponsableIndicateurDetails.getDesignation());
		ResponsableIndicateur.setNom(ResponsableIndicateurDetails.getNom());
		ResponsableIndicateur.setPrenom(ResponsableIndicateurDetails.getPrenom());
		ResponsableIndicateur.setTel(ResponsableIndicateurDetails.getTel());
		ResponsableIndicateur.setEmail(ResponsableIndicateurDetails.getEmail());
		ResponsableIndicateurRepo.save(ResponsableIndicateur);
		return ResponseEntity.ok().body("ResponsableIndicateur updated");
	}

	@DeleteMapping("/responsable/{id}")
	public ResponseEntity<String> deleteResponsableIndicateur(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		ResponsableIndicateurservice.delete(id);
		return ResponseEntity.ok().body("Type deleted");
	}
}
