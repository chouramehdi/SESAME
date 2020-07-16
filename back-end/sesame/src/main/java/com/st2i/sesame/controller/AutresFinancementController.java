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

import com.st2i.sesame.entities.AutresFinancement;
import com.st2i.sesame.entities.pojo.AutresFinancementPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.AutresFinancementRepository;
import com.st2i.sesame.service.AutresFinancementService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AutresFinancementController {

	@Autowired
	AutresFinancementRepository autresFinancementRepo;
	@Autowired
	AutresFinancementService autresFinancementservice;

	@PostMapping("/autresfinancement")
	public AutresFinancement addAutresFinancement(@Valid @RequestBody AutresFinancement AutresFinancement) {
		autresFinancementservice.addAutresFinancement(AutresFinancement);
		return AutresFinancement;
	}

	@GetMapping("/autresfinancement")
	public Set<AutresFinancementPojo> getAutresFinancements() {
		List<AutresFinancement> AutresFinancementList = autresFinancementservice.getAll();
		Set<AutresFinancementPojo> AutresFinancements = new HashSet<>();
		AutresFinancementList.forEach(AutresFinancement -> {
			AutresFinancementPojo _AutresFinancement = new AutresFinancementPojo();
			_AutresFinancement.setId(AutresFinancement.getId());
			_AutresFinancement.setCode(AutresFinancement.getCode());
			_AutresFinancement.setDesignation(AutresFinancement.getDesignation());
			_AutresFinancement.setValeur(AutresFinancement.getValeur());
			AutresFinancements.add(_AutresFinancement);
		});
		return AutresFinancements;
	}

	@GetMapping("/autresfinancement/{id}")
	public ResponseEntity<AutresFinancementPojo> getAutresFinancementById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		AutresFinancement AutresFinancement = autresFinancementRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("AutresFinancement not found for this id : " + id));
		AutresFinancementPojo _AutresFinancement = new AutresFinancementPojo();
		_AutresFinancement.setCode(AutresFinancement.getCode());
		_AutresFinancement.setId(AutresFinancement.getId());
		_AutresFinancement.setDesignation(AutresFinancement.getDesignation());
		_AutresFinancement.setValeur(AutresFinancement.getValeur());
		return ResponseEntity.ok().body(_AutresFinancement);
	}

	@PutMapping("/autresfinancement")
	public ResponseEntity<String> updateAutresFinancement(
			@Valid @RequestBody AutresFinancementPojo AutresFinancementDetails) throws ResourceNotFoundException {
		AutresFinancement AutresFinancement = autresFinancementRepo
				.findById(AutresFinancementDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Role not found for this id : " + AutresFinancementDetails.getId()));

		AutresFinancement.setCode(AutresFinancementDetails.getCode());
		AutresFinancement.setDesignation(AutresFinancementDetails.getDesignation());
		AutresFinancement.setValeur(AutresFinancementDetails.getValeur());
		autresFinancementRepo.save(AutresFinancement);
		return ResponseEntity.ok().body("AutresFinancement updated");
	}

	@DeleteMapping("/autresfinancement/{id}")
	public ResponseEntity<String> deleteAutresFinancement(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		autresFinancementservice.delete(id);
		return ResponseEntity.ok().body("Autres financement deleted");
	}
	
	@GetMapping("/autresfinancement/{designation}")
	public ResponseEntity<AutresFinancement> getAutresFinancementByDesignation(@PathVariable(value = "designation") String designation){
		AutresFinancement autresFinancement =autresFinancementservice.findByDesignation(designation);
		return ResponseEntity.ok().body(autresFinancement);
	}
}
