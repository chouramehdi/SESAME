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

import com.st2i.sesame.entities.NatureIndicateur;
import com.st2i.sesame.entities.pojo.NatureIndicateurPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.NatureIndicateurRepository;
import com.st2i.sesame.service.NatureIndicateurService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NatureIndicateurController {
	
	@Autowired NatureIndicateurRepository NatureIndicateurRepo;
	@Autowired NatureIndicateurService NatureIndicateurservice;
	
	@PostMapping("/natureindicateur")
	public NatureIndicateur addNatureIndicateur(@Valid @RequestBody NatureIndicateur NatureIndicateur) {
		NatureIndicateurservice.addNatureIndicateur(NatureIndicateur);
		return NatureIndicateur;
	}
	
	@GetMapping("/natureindicateur")
	public Set<NatureIndicateurPojo> getNatureIndicateurs() {
		List<NatureIndicateur> NatureIndicateurList = NatureIndicateurservice.getAll();
		Set<NatureIndicateurPojo> NatureIndicateurs = new HashSet<>();
		NatureIndicateurList.forEach(NatureIndicateur ->{
			NatureIndicateurPojo _NatureIndicateur = new NatureIndicateurPojo();
			_NatureIndicateur.setId(NatureIndicateur.getId());
			_NatureIndicateur.setCode(NatureIndicateur.getCode());
			_NatureIndicateur.setDesignation(NatureIndicateur.getDesignation());
			NatureIndicateurs.add(_NatureIndicateur);
		});
		return NatureIndicateurs;
	}
	
	@GetMapping("/natureindicateur/{id}")
	public ResponseEntity<NatureIndicateurPojo> getNatureIndicateurById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		NatureIndicateur NatureIndicateur = NatureIndicateurRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("NatureIndicateur not found for this id : " + id));
		NatureIndicateurPojo _NatureIndicateur = new NatureIndicateurPojo();
		_NatureIndicateur.setCode(NatureIndicateur.getCode());
		_NatureIndicateur.setId(NatureIndicateur.getId());
		_NatureIndicateur.setDesignation(NatureIndicateur.getDesignation());
		return ResponseEntity.ok().body(_NatureIndicateur);
	}
	
	@PutMapping("/natureindicateur")
	public ResponseEntity<String> updateNatureIndicateur( 
			@Valid @RequestBody NatureIndicateurPojo NatureIndicateurDetails) throws ResourceNotFoundException {
		NatureIndicateur NatureIndicateur = NatureIndicateurRepo.findById(NatureIndicateurDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Nature not found for this id : " + NatureIndicateurDetails.getId()));
		
		NatureIndicateur.setCode(NatureIndicateurDetails.getCode());
		NatureIndicateur.setDesignation(NatureIndicateurDetails.getDesignation());
		NatureIndicateurRepo.save(NatureIndicateur);
		return ResponseEntity.ok().body("NatureIndicateur updated");
	}

	@DeleteMapping("/natureindicateur/{id}")
	public ResponseEntity<String> deleteNatureIndicateur(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		NatureIndicateurservice.delete(id);
		return ResponseEntity.ok().body("Nature deleted");
	}
}
