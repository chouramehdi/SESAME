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

import com.st2i.sesame.entities.NatureDecoupage;
import com.st2i.sesame.entities.pojo.NatureDecoupagePojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.NatureDecoupageRepository;
import com.st2i.sesame.service.NatureDecoupageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NatureDecoupageController {
	@Autowired NatureDecoupageRepository natureDecoupageRepo;
	@Autowired NatureDecoupageService natureDecoupageservice;
	
	@PostMapping("/naturedecoupage")
	public NatureDecoupage addNatureDecoupage(@Valid @RequestBody NatureDecoupage NatureDecoupage) {
		natureDecoupageservice.addNatureDecoupage(NatureDecoupage);
		return NatureDecoupage;
	}
	
	@GetMapping("/naturedecoupage")
	public Set<NatureDecoupagePojo> getNatureDecoupages() {
		List<NatureDecoupage> NatureDecoupageList = natureDecoupageservice.getAll();
		Set<NatureDecoupagePojo> NatureDecoupages = new HashSet<>();
		NatureDecoupageList.forEach(NatureDecoupage ->{
			NatureDecoupagePojo _NatureDecoupage = new NatureDecoupagePojo();
			_NatureDecoupage.setId(NatureDecoupage.getId());
			_NatureDecoupage.setCode(NatureDecoupage.getCode());
			_NatureDecoupage.setDesignation(NatureDecoupage.getDesignation());
			NatureDecoupages.add(_NatureDecoupage);
		});
		return NatureDecoupages;
	}
	
	@GetMapping("/naturedecoupage/{id}")
	public ResponseEntity<NatureDecoupagePojo> getNatureDecoupageById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		NatureDecoupage NatureDecoupage = natureDecoupageRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("NatureDecoupage not found for this id : " + id));
		NatureDecoupagePojo _NatureDecoupage = new NatureDecoupagePojo();
		_NatureDecoupage.setCode(NatureDecoupage.getCode());
		_NatureDecoupage.setId(NatureDecoupage.getId());
		_NatureDecoupage.setDesignation(NatureDecoupage.getDesignation());
		return ResponseEntity.ok().body(_NatureDecoupage);
	}
	
	@PutMapping("/naturedecoupage")
	public ResponseEntity<String> updateNatureDecoupage( 
			@Valid @RequestBody NatureDecoupagePojo NatureDecoupageDetails) throws ResourceNotFoundException {
		NatureDecoupage NatureDecoupage = natureDecoupageRepo.findById(NatureDecoupageDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("NatureDecoupage not found for this id : " + NatureDecoupageDetails.getId()));
		
		NatureDecoupage.setCode(NatureDecoupageDetails.getCode());
		NatureDecoupage.setDesignation(NatureDecoupageDetails.getDesignation());
		natureDecoupageRepo.save(NatureDecoupage);
		return ResponseEntity.ok().body("NatureDecoupage updated");
	}

	@DeleteMapping("/naturedecoupage/{id}")
	public ResponseEntity<String> deleteNatureDecoupage(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		natureDecoupageservice.delete(id);
		return ResponseEntity.ok().body("NatureDecoupage deleted");
	}
}	
