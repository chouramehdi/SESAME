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

import com.st2i.sesame.entities.Financement;
import com.st2i.sesame.entities.pojo.FinancementPojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.FinancementRepository;
import com.st2i.sesame.service.AutresFinancementService;
import com.st2i.sesame.service.BailleurService;
import com.st2i.sesame.service.CategorieService;
import com.st2i.sesame.service.DeviseService;
import com.st2i.sesame.service.FinancementService;
import com.st2i.sesame.service.NatureFinancementService;
import com.st2i.sesame.service.TypeFinancementService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FinancementController {
	@Autowired
	FinancementRepository FinancementRepo;
	@Autowired
	FinancementService Financementservice;
	@Autowired
	DeviseService devise;
	@Autowired
	CategorieService categorie;
	@Autowired
	AutresFinancementService autresfinance;
	@Autowired
	BailleurService bailleur;
	@Autowired
	TypeFinancementService type;
	@Autowired
	NatureFinancementService nature;
	
	@GetMapping("/financementpojo/{id}")
	public FinancementPojo getFinancementPojo(@PathVariable(value = "id") Integer id) {
	Financement financement = Financementservice.findOne(id);
	FinancementPojo financementpojo =new FinancementPojo();
	financementpojo.setId(id);
	financementpojo.setCode(financement.getCode());
	financementpojo.setDesignation(financement.getDesignation());
	financementpojo.setValeur(financement.getValeur());
	financementpojo.setId_categorie(financement.getCategorie().getId());
	financementpojo.setId_devise(financement.getDevise().getId());
	financementpojo.setId_nature(financement.getNature().getId());
	financementpojo.setId_type(financement.getType().getId());
	if(financement.getAutresFinancement() != null)
		financementpojo.setId_autresfinancement(financement.getAutresFinancement().getId());
	if(financement.getBailleur() != null)
		financementpojo.setId_bailleur(financement.getBailleur().getId());
	return financementpojo;
	}
	
	@PostMapping("/financement")
	public Financement addFinancement(@Valid @RequestBody FinancementPojo FinancePojo) {
		Financement Financement = new Financement();
		Financement.setCode(FinancePojo.getCode());
		Financement.setId(FinancePojo.getId());
		Financement.setDesignation(FinancePojo.getDesignation());
		Financement.setDevise(devise.findOne(FinancePojo.getId_devise()));
		Financement.setCategorie(categorie.findOne(FinancePojo.getId_categorie()));
		Financement.setValeur(FinancePojo.getValeur());
		if (FinancePojo.getId_autresfinancement() != null)
			Financement.setAutresFinancement(autresfinance.findOne(FinancePojo.getId_autresfinancement()));
		if (FinancePojo.getId_bailleur() != null)
			Financement.setBailleur(bailleur.findOne(FinancePojo.getId_bailleur()));
		Financement.setNature(nature.findOne(FinancePojo.getId_nature()));
		Financement.setType(type.findOne(FinancePojo.getId_type()));

		Financementservice.addFinancement(Financement);
		return Financement;
	}

	@GetMapping("/financement")
	public List<Financement> getFinancements() {
		return Financementservice.getAll();
	}

	@GetMapping("/financement/{id}")
	public ResponseEntity<Financement> getFinancementById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Financement Financement = FinancementRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Financement not found for this id : " + id));
		return ResponseEntity.ok().body(Financement);
	}

	@PutMapping("/financement")
	public ResponseEntity<String> updateFinancement(@Valid @RequestBody FinancementPojo FinancePojo)
			throws ResourceNotFoundException {
		Financement Financement = FinancementRepo.findById(FinancePojo.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Financement not found for this id : " + FinancePojo.getId()));

		Financement.setCode(FinancePojo.getCode());
		Financement.setId(FinancePojo.getId());
		Financement.setDesignation(FinancePojo.getDesignation());
		Financement.setDevise(devise.findOne(FinancePojo.getId_devise()));
		Financement.setCategorie(categorie.findOne(FinancePojo.getId_categorie()));
		Financement.setValeur(FinancePojo.getValeur());
		if (FinancePojo.getId_autresfinancement() != null) {
			Financement.setAutresFinancement(autresfinance.findOne(FinancePojo.getId_autresfinancement()));
			Financement.setBailleur(null);
		}
		if (FinancePojo.getId_bailleur() != null) {
			Financement.setBailleur(bailleur.findOne(FinancePojo.getId_bailleur()));
			Financement.setAutresFinancement(null);
		}
		Financement.setType(type.findOne(FinancePojo.getId_type()));
		Financement.setNature(nature.findOne(FinancePojo.getId_nature()));
		FinancementRepo.save(Financement);
		return ResponseEntity.ok().body("Financement updated");
	}

	@DeleteMapping("/financement/{id}")
	public ResponseEntity<String> deleteFinancement(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Financementservice.delete(id);
		return ResponseEntity.ok().body("Financement deleted");
	}
}
