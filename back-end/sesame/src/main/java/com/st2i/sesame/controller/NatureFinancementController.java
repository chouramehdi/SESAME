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

import com.st2i.sesame.entities.NatureFinancement;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.service.NatureFinancementService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NatureFinancementController {
	
	@Autowired NatureFinancementService natureFinancementservice;
	
	@PostMapping("/naturefinancement")
	public NatureFinancement addNatureFinancement(@Valid @RequestBody NatureFinancement NatureFinancement) {
		natureFinancementservice.addNatureFinancement(NatureFinancement);
		return NatureFinancement;
	}
	
	@GetMapping("/naturefinancement")
	public List<NatureFinancement> getNatureFinancements() {
		return natureFinancementservice.getAll();
	}
	
	@GetMapping("/naturefinancement/{id}")
	public ResponseEntity<NatureFinancement> getNatureFinancementById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		NatureFinancement NatureFinancement = natureFinancementservice.findOne(id);
		return ResponseEntity.ok().body(NatureFinancement);
	}
	
	@PutMapping("/naturefinancement")
	public ResponseEntity<String> updateNatureFinancement( 
			@Valid @RequestBody NatureFinancement NatureFinancementDetails) throws ResourceNotFoundException {
		NatureFinancement NatureFinancement = natureFinancementservice.findOne(NatureFinancementDetails.getId());
		
		NatureFinancement.setCode(NatureFinancementDetails.getCode());
		NatureFinancement.setDesignation(NatureFinancementDetails.getDesignation());
		natureFinancementservice.addNatureFinancement(NatureFinancement);	
		return ResponseEntity.ok().body("NatureFinancement updated");
	}

	@DeleteMapping("/naturefinancement/{id}")
	public ResponseEntity<String> deleteNatureFinancement(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		natureFinancementservice.delete(id);
		return ResponseEntity.ok().body("NatureFinancement deleted");
	}
	
	@GetMapping("/naturefinancement/{designation}")
	public ResponseEntity<NatureFinancement> getNatureByDesignation(@PathVariable(value = "designation") String designation){
		NatureFinancement  nature =natureFinancementservice.findByDesignation(designation);
		return ResponseEntity.ok().body(nature);
	}
}
