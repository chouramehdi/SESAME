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

import com.st2i.sesame.entities.SecteurDecoupage;
import com.st2i.sesame.entities.pojo.SecteurDecoupagePojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.SecteurDecoupageRepository;
import com.st2i.sesame.service.SecteurDecoupageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SecteurDecoupageController {
	
	@Autowired SecteurDecoupageRepository SecteurdecoupageRepo;
	@Autowired SecteurDecoupageService Secteurdecoupageservice;
	
	@PostMapping("/secteurdecoupage")
	public SecteurDecoupage addSecteurDecoupage(@Valid @RequestBody SecteurDecoupage SecteurDecoupage) {
		Secteurdecoupageservice.addSecteurDecoupage(SecteurDecoupage);
		return SecteurDecoupage;
	}
	
	@GetMapping("/secteurdecoupage")
	public Set<SecteurDecoupagePojo> getSecteurDecoupages() {
		List<SecteurDecoupage> SecteurDecoupageList = Secteurdecoupageservice.getAll();
		Set<SecteurDecoupagePojo> SecteurDecoupages = new HashSet<>();
		SecteurDecoupageList.forEach(SecteurDecoupage ->{
			SecteurDecoupagePojo _SecteurDecoupage = new SecteurDecoupagePojo();
			_SecteurDecoupage.setId(SecteurDecoupage.getId());
			_SecteurDecoupage.setCode(SecteurDecoupage.getCode());
			_SecteurDecoupage.setDesignation(SecteurDecoupage.getDesignation());
			SecteurDecoupages.add(_SecteurDecoupage);
		});
		return SecteurDecoupages;
	}
	
	@GetMapping("/secteurdecoupage/{id}")
	public ResponseEntity<SecteurDecoupagePojo> getSecteurDecoupageById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		SecteurDecoupage SecteurDecoupage = SecteurdecoupageRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SecteurDecoupage not found for this id : " + id));
		SecteurDecoupagePojo _SecteurDecoupage = new SecteurDecoupagePojo();
		_SecteurDecoupage.setCode(SecteurDecoupage.getCode());
		_SecteurDecoupage.setId(SecteurDecoupage.getId());
		_SecteurDecoupage.setDesignation(SecteurDecoupage.getDesignation());
		return ResponseEntity.ok().body(_SecteurDecoupage);
	}
	
	@PutMapping("/secteurdecoupage")
	public ResponseEntity<String> updateSecteurDecoupage( 
			@Valid @RequestBody SecteurDecoupagePojo SecteurDecoupageDetails) throws ResourceNotFoundException {
		SecteurDecoupage SecteurDecoupage = SecteurdecoupageRepo.findById(SecteurDecoupageDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Secteur not found for this id : " + SecteurDecoupageDetails.getId()));
		
		SecteurDecoupage.setCode(SecteurDecoupageDetails.getCode());
		SecteurDecoupage.setDesignation(SecteurDecoupageDetails.getDesignation());
		SecteurdecoupageRepo.save(SecteurDecoupage);
		return ResponseEntity.ok().body("SecteurDecoupage updated");
	}

	@DeleteMapping("/secteurdecoupage/{id}")
	public ResponseEntity<String> deleteSecteurDecoupage(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Secteurdecoupageservice.delete(id);
		return ResponseEntity.ok().body("Secteur deleted");
	}
}
