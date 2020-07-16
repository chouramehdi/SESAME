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

import com.st2i.sesame.entities.Categorie;
import com.st2i.sesame.entities.pojo.CategoriePojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.CategorieRepository;
import com.st2i.sesame.service.CategorieService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategorieController {
	
	@Autowired CategorieService categorieservice;
	@Autowired CategorieRepository categorieRepo;
	
	@PostMapping("/categorie")
	public Categorie addcategorie(@Valid @RequestBody Categorie categorie) {
		categorieservice.addCategorie(categorie);
		return categorie;
	}
	
	@GetMapping("/categorie")
	public Set<CategoriePojo> getcategories() {
		List<Categorie> categorieList = categorieservice.getAll();
		Set<CategoriePojo> categories = new HashSet<>();
		categorieList.forEach(categorie ->{
			CategoriePojo _categorie = new CategoriePojo();
			_categorie.setId(categorie.getId());
			_categorie.setCode(categorie.getCode());
			_categorie.setDesignation(categorie.getDesignation());
			categories.add(_categorie);
		});
		return categories;
	}
	
	@GetMapping("/categorie/{id}")
	public ResponseEntity<CategoriePojo> getcategorieById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Categorie categorie = categorieRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("categorie not found for this id : " + id));
		CategoriePojo _categorie = new CategoriePojo();
		_categorie.setCode(categorie.getCode());
		_categorie.setId(categorie.getId());
		_categorie.setDesignation(categorie.getDesignation());
		return ResponseEntity.ok().body(_categorie);
	}
	
	@PutMapping("/categorie")
	public ResponseEntity<String> updatecategorie( 
			@Valid @RequestBody CategoriePojo categorieDetails) throws ResourceNotFoundException {
		Categorie categorie = categorieRepo.findById(categorieDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id : " + categorieDetails.getId()));
		
		categorie.setCode(categorieDetails.getCode());
		categorie.setDesignation(categorieDetails.getDesignation());
		categorieRepo.save(categorie);
		return ResponseEntity.ok().body("categorie updated");
	}

	@DeleteMapping("/categorie/{id}")
	public ResponseEntity<String> deletecategorie(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		categorieservice.delete(id);
		return ResponseEntity.ok().body("Categorie deleted");
	}
	
	@GetMapping("/categorie/{designation}")
	public ResponseEntity<Categorie> getBailleurByDesignation(@PathVariable(value = "designation") String designation){
		Categorie  categorie =categorieservice.findByDesignation(designation);
		return ResponseEntity.ok().body(categorie);
	}
}
