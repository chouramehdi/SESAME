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

import com.st2i.sesame.entities.Indicateur;
import com.st2i.sesame.entities.pojo.IndicateurPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.IndicateurRepository;
import com.st2i.sesame.service.IndicateurService;
import com.st2i.sesame.service.NatureIndicateurService;
import com.st2i.sesame.service.ResponsableIndicateurService;
import com.st2i.sesame.service.TypeIndicateurService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class IndicateurController {
	@Autowired IndicateurRepository indicateurRepo;
	@Autowired IndicateurService indicateurservice;
	@Autowired NatureIndicateurService natureindicateur;
	@Autowired TypeIndicateurService typeindicateur;
	@Autowired ResponsableIndicateurService responsable;
	
	@GetMapping("/indicateurpojo/{id}")
	public IndicateurPojo getIndicateurPojo(@PathVariable(value = "id") Integer id) {
		Indicateur indicateur = indicateurservice.findOne(id);
		IndicateurPojo indicateurpojo = new IndicateurPojo();
		indicateurpojo.setCode(indicateur.getCode());
		indicateurpojo.setDesignation(indicateur.getDesignation());
		indicateurpojo.setId(indicateur.getId());
		indicateurpojo.setId_nature(indicateur.getNature().getId());
		indicateurpojo.setId_responsable(indicateur.getResponsable().getId());
		indicateurpojo.setReference(indicateur.getReference());
		indicateurpojo.setUnite(indicateur.getUnite());
		indicateurpojo.setId_type(indicateur.getType().getId());
		indicateurpojo.setPoids(indicateur.getPoids());
		Set<Integer> id_decoupages = new HashSet<>();

		indicateurpojo.setDecoupages(id_decoupages);
		return indicateurpojo;
	}
	
	@PostMapping("/indicateur")
	public Indicateur addIndicateur(@Valid @RequestBody IndicateurPojo indicateurpojo) {
		Indicateur indicateur = new Indicateur();
		indicateur.setCode(indicateurpojo.getCode());
		indicateur.setDesignation(indicateurpojo.getDesignation());
		indicateur.setId(indicateurpojo.getId());
		indicateur.setNature(natureindicateur.findOne(indicateurpojo.getId_nature()));
		indicateur.setReference(indicateurpojo.getReference());
		indicateur.setResponsable(responsable.findOne(indicateurpojo.getId_responsable()));
		indicateur.setType(typeindicateur.findOne(indicateurpojo.getId_type()));
		indicateur.setUnite(indicateurpojo.getUnite());
		indicateur.setPoids(indicateurpojo.getPoids());
		indicateurservice.addIndicateur(indicateur);
		
		return indicateur;
	}
	
	@GetMapping("/indicateur")
	public List<Indicateur> getallIndicateur() {
		return indicateurservice.getAll();
	}
	
	@GetMapping("/indicateur/{id}")
	public ResponseEntity<Indicateur> getIndicateurById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Indicateur Indicateur = indicateurRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Indicateur not found for this id : " + id));
		return ResponseEntity.ok().body(Indicateur);
	}
	@PutMapping("/indicateur")
	public ResponseEntity<String> updateIndicateur(@Valid @RequestBody IndicateurPojo IndicateurPojo) throws ResourceNotFoundException{
		Indicateur indicateur = indicateurRepo.findById(IndicateurPojo.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Financement not found for this id : " + IndicateurPojo.getId()));
		indicateur.setCode(IndicateurPojo.getCode());
		indicateur.setDesignation(IndicateurPojo.getDesignation());
		indicateur.setNature(natureindicateur.findOne(IndicateurPojo.getId_nature()));
		indicateur.setReference(IndicateurPojo.getReference());
		indicateur.setResponsable(responsable.findOne(IndicateurPojo.getId_responsable()));
		indicateur.setType(typeindicateur.findOne(IndicateurPojo.getId_type()));
		indicateur.setUnite(IndicateurPojo.getUnite());
		indicateur.setPoids(IndicateurPojo.getPoids());
		indicateurRepo.save(indicateur);
		return ResponseEntity.ok().body("Indicateur supprimé");
	}
	
	@DeleteMapping("/indicateur/{id}")
	public ResponseEntity<String> deleteIndicateur(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		indicateurservice.delete(id);
		return ResponseEntity.ok().body("Indicateur supprimé");
	}
}
