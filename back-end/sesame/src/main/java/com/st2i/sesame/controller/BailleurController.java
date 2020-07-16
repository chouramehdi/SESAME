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

import com.st2i.sesame.entities.Bailleur;
import com.st2i.sesame.entities.pojo.BailleurPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.BailleurRepository;
import com.st2i.sesame.service.BailleurService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BailleurController {
	@Autowired BailleurService bailleurservice;
	@Autowired BailleurRepository bailleurRepo;
	
	@PostMapping("/bailleur")
	public Bailleur addBailleur(@Valid @RequestBody Bailleur bailleur) {
		bailleurservice.addBailleur(bailleur);
		return bailleur;
	}
	
	@GetMapping("/bailleur")
	public Set<BailleurPojo> getcathegories() {
		List<Bailleur> bailleurList = bailleurservice.getAll();
		Set<BailleurPojo> bailleurs = new HashSet<>();
		bailleurList.forEach(bailleur->{
			BailleurPojo _bailleur= new BailleurPojo();
			_bailleur.setId(bailleur.getId());
			_bailleur.setCode(bailleur.getCode());
			_bailleur.setDesignation(bailleur.getDesignation());
			bailleurs.add(_bailleur);
		});
		return bailleurs;
	}
	
	@GetMapping("/bailleur/{id}")
	public ResponseEntity<BailleurPojo> getBailleurById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Bailleur bailleur = bailleurRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bailleur not found for this id : " + id));
		BailleurPojo _bailleur = new BailleurPojo();
		_bailleur.setCode(bailleur.getCode());
		_bailleur.setId(bailleur.getId());
		_bailleur.setDesignation(bailleur.getDesignation());
		return ResponseEntity.ok().body(_bailleur);
	}
	
	@PutMapping("/bailleur")
	public ResponseEntity<String> updateBailleur(
			@Valid @RequestBody BailleurPojo bailleurDetails) throws ResourceNotFoundException {
		Bailleur bailleur = bailleurRepo.findById(bailleurDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id : " + bailleurDetails.getId()));
		
		bailleur.setCode(bailleurDetails.getCode());
		bailleur.setDesignation(bailleurDetails.getDesignation());
		bailleurRepo.save(bailleur);
		return ResponseEntity.ok().body("Bailleur updated");
	}

	@DeleteMapping("/bailleur/{id}")
	public ResponseEntity<String> deleteBailleur(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		bailleurservice.delete(id);
		return ResponseEntity.ok().body("Bailleur deleted");
	}
	@GetMapping("/bailleur/{designation}")
	public ResponseEntity<Bailleur> getBailleurByDesignation(@PathVariable(value = "designation") String designation){
		Bailleur bailleur =bailleurservice.findByDesignation(designation);
		return ResponseEntity.ok().body(bailleur);
	}
}
