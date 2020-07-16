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

import com.st2i.sesame.entities.PrioriteDecoupage;
import com.st2i.sesame.entities.pojo.PrioriteDecoupagePojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.PrioriteDecoupageRepository;
import com.st2i.sesame.service.PrioriteDecoupageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PrioriteDecoupageController {
	@Autowired PrioriteDecoupageRepository PrioriteDecoupageRepo;
	@Autowired PrioriteDecoupageService PrioriteDecoupageservice;
	
	@PostMapping("/prioritedecoupage")
	public PrioriteDecoupage addPrioriteDecoupage(@Valid @RequestBody PrioriteDecoupage PrioriteDecoupage) {
		PrioriteDecoupageservice.addPrioriteDecoupage(PrioriteDecoupage);
		return PrioriteDecoupage;
	}
	
	@GetMapping("/prioritedecoupage")
	public Set<PrioriteDecoupagePojo> getPrioriteDecoupages() {
		List<PrioriteDecoupage> PrioriteDecoupageList = PrioriteDecoupageservice.getAll();
		Set<PrioriteDecoupagePojo> PrioriteDecoupages = new HashSet<>();
		PrioriteDecoupageList.forEach(PrioriteDecoupage ->{
			PrioriteDecoupagePojo _PrioriteDecoupage = new PrioriteDecoupagePojo();
			_PrioriteDecoupage.setId(PrioriteDecoupage.getId());
			_PrioriteDecoupage.setCode(PrioriteDecoupage.getCode());
			_PrioriteDecoupage.setDesignation(PrioriteDecoupage.getDesignation());
			PrioriteDecoupages.add(_PrioriteDecoupage);
		});
		return PrioriteDecoupages;
	}
	
	@GetMapping("/prioritedecoupage/{id}")
	public ResponseEntity<PrioriteDecoupagePojo> getPrioriteDecoupageById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		PrioriteDecoupage PrioriteDecoupage = PrioriteDecoupageRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("PrioriteDecoupage not found for this id : " + id));
		PrioriteDecoupagePojo _PrioriteDecoupage = new PrioriteDecoupagePojo();
		_PrioriteDecoupage.setCode(PrioriteDecoupage.getCode());
		_PrioriteDecoupage.setId(PrioriteDecoupage.getId());
		_PrioriteDecoupage.setDesignation(PrioriteDecoupage.getDesignation());
		return ResponseEntity.ok().body(_PrioriteDecoupage);
	}
	
	@PutMapping("/prioritedecoupage")
	public ResponseEntity<String> updatePrioriteDecoupage( 
			@Valid @RequestBody PrioriteDecoupagePojo PrioriteDecoupageDetails) throws ResourceNotFoundException {
		PrioriteDecoupage PrioriteDecoupage = PrioriteDecoupageRepo.findById(PrioriteDecoupageDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("PrioriteDecoupage not found for this id : " + PrioriteDecoupageDetails.getId()));
		
		PrioriteDecoupage.setCode(PrioriteDecoupageDetails.getCode());
		PrioriteDecoupage.setDesignation(PrioriteDecoupageDetails.getDesignation());
		PrioriteDecoupageRepo.save(PrioriteDecoupage);
		return ResponseEntity.ok().body("PrioriteDecoupage updated");
	}

	@DeleteMapping("/prioritedecoupage/{id}")
	public ResponseEntity<String> deletePrioriteDecoupage(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		PrioriteDecoupageservice.delete(id);
		return ResponseEntity.ok().body("PrioriteDecoupage deleted");
	}
}	
