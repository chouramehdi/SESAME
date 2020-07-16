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

import com.st2i.sesame.entities.DecoupageAnalytique;
import com.st2i.sesame.entities.Indicateur;
import com.st2i.sesame.entities.pojo.DecoupageAnalytiquePojo;
import com.st2i.sesame.exception.ResourceNotFoundException;
import com.st2i.sesame.repository.DecoupageAnalytiqueRepository;
import com.st2i.sesame.service.DecoupageAnalytiqueService;
import com.st2i.sesame.service.IndicateurService;
import com.st2i.sesame.service.NatureDecoupageService;
import com.st2i.sesame.service.PrioriteDecoupageService;
import com.st2i.sesame.service.SecteurDecoupageService;
import com.st2i.sesame.service.TypeDecoupageService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DecoupageAnalytiqueController {
	@Autowired DecoupageAnalytiqueService decoupageservice;
	@Autowired DecoupageAnalytiqueRepository decoupageRepo;
	@Autowired NatureDecoupageService naturedecoupage;
	@Autowired TypeDecoupageService typedecoupage;
	@Autowired SecteurDecoupageService secteurdecoupage;
	@Autowired PrioriteDecoupageService prioritedecoupage;
	@Autowired IndicateurService indicateurservice;
	
	@PostMapping("/decoupage")
	public DecoupageAnalytique addDecoupage(@Valid @RequestBody DecoupageAnalytiquePojo DecoupagePojo) {
		DecoupageAnalytique decoupage = new DecoupageAnalytique();
		decoupage.setCode(DecoupagePojo.getCode());
		decoupage.setDesignation(DecoupagePojo.getDesignation());
		if(DecoupagePojo.getId_parent() == 1)
			DecoupagePojo.setId_parent(null);
		decoupage.setId_parent(DecoupagePojo.getId_parent());
		decoupage.setPoids((DecoupagePojo.getPoids()));
		decoupage.setNature(naturedecoupage.findOne(DecoupagePojo.getId_nature()));
		decoupage.setPriorite(prioritedecoupage.findOne(DecoupagePojo.getId_priorite()));
		decoupage.setType(typedecoupage.findOne(DecoupagePojo.getId_type()));
		decoupage.setSecteur(secteurdecoupage.findOne(DecoupagePojo.getId_secteur()));
		decoupage.setId(DecoupagePojo.getId());
		DecoupagePojo.getIndicateurs().forEach(element->{
			decoupage.getIndicateurs().add(indicateurservice.findOne(element));
		});
		
		decoupageservice.addDecoupageAnalytique(decoupage);
		return decoupage;
	}
	@GetMapping("/decoupage")
	public List<DecoupageAnalytique> getDecoupage(){
		List<DecoupageAnalytique> decoupages =decoupageservice.getAll();
		decoupages.forEach(decoupage ->{	
			if(!decoupage.getIndicateurs().isEmpty()) {
				decoupage.getIndicateurs().forEach(indicateur->{
					double avancement = 0;
					avancement+=(indicateur.getReference()*(indicateur.getPoids()/100));
					decoupage.setAvancement(avancement);
			}
			);}
			Set<Integer> filslist = decoupageservice.getFils(decoupage.getId());
			filslist.forEach(fils->{
				DecoupageAnalytique decoupagefils=decoupageservice.findOne(fils);
				decoupagefils.getIndicateurs().forEach(indice->{
					double avancementfils = decoupagefils.getAvancement();
					avancementfils+=indice.getReference()*(indice.getPoids()/100);
					decoupagefils.setAvancement(avancementfils);
				});
				double avancement = decoupage.getAvancement();
				avancement+=decoupagefils.getAvancement()*(decoupagefils.getPoids()/100);
				decoupage.setAvancement(avancement);
			});
		});
		decoupageRepo.saveAll(decoupages);
		return decoupages;
	}
	
	@GetMapping("/decoupage/{id}")
	public ResponseEntity<DecoupageAnalytique> getDecoupageAnalytiquetById(@PathVariable(value = "id") Integer id)throws ResourceNotFoundException {
		DecoupageAnalytique DecoupageAnalytique = decoupageRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("DecoupageAnalytique not found for this id : " + id));
		return ResponseEntity.ok().body(DecoupageAnalytique);
	}
	
	@PutMapping("/decoupage")
	public ResponseEntity<String> updateDecoupage(@Valid @RequestBody DecoupageAnalytiquePojo DecoupagePojo)
			throws ResourceNotFoundException {
		DecoupageAnalytique decoupage = decoupageRepo.findById(DecoupagePojo.getId()).orElseThrow(
				() -> new ResourceNotFoundException("DecoupageAnalytique not found for this id : " + DecoupagePojo.getId()));
		decoupage.setCode(DecoupagePojo.getCode());
		decoupage.setDesignation(DecoupagePojo.getDesignation());
		decoupage.setId_parent(DecoupagePojo.getId_parent());
		decoupage.setNature(naturedecoupage.findOne(DecoupagePojo.getId_nature()));
		decoupage.setType(typedecoupage.findOne(DecoupagePojo.getId_type()));
		decoupage.setPriorite(prioritedecoupage.findOne(DecoupagePojo.getId_priorite()));
		decoupage.setSecteur(secteurdecoupage.findOne(DecoupagePojo.getId_secteur()));
		decoupage.setId(DecoupagePojo.getId());
		Set<Indicateur> d = new HashSet<>();
		decoupage.setIndicateurs(d);
		DecoupagePojo.getIndicateurs().forEach(element->{
			decoupage.getIndicateurs().add(indicateurservice.findOne(element));
		});
		decoupageRepo.save(decoupage);
		return ResponseEntity.ok().body("Decopage updated");
		}
	
	@DeleteMapping("/decoupage/{id}")
	public ResponseEntity<String> deleteDecoupage(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
		Set<Integer> parents = new HashSet<>();
		parents.add(id);
		DecoupageAnalytique decoupage = decoupageservice.findOne(id);
		Integer current_id = decoupage.getId_parent();
		while(current_id != null) {
			parents.add(current_id);
			decoupage = decoupageservice.findOne(current_id);
			current_id = decoupage.getId_parent();
		}
		parents.forEach(id_parent->{
			decoupageservice.findOne(id_parent).setIndicateurs(null);
			decoupageservice.delete(id_parent);
		});
		return ResponseEntity.ok().body("Decoupages deleted");
	}
	
	@GetMapping("/decoupagepojo/{id}")
	public DecoupageAnalytiquePojo getdecoupagepojo (@PathVariable(value = "id") Integer id) {
		DecoupageAnalytique decoupage=decoupageservice.findOne(id);
		DecoupageAnalytiquePojo decoupagepojo =new DecoupageAnalytiquePojo();
		decoupagepojo.setCode(decoupage.getCode());
		decoupagepojo.setDesignation(decoupage.getDesignation());
		decoupagepojo.setId(id);
		decoupagepojo.setId_nature(decoupage.getNature().getId());
		decoupagepojo.setId_parent(decoupage.getId_parent());
		decoupagepojo.setId_priorite(decoupage.getPriorite().getId());
		decoupagepojo.setId_secteur(decoupage.getSecteur().getId());
		decoupagepojo.setId_type(decoupage.getType().getId());
		decoupagepojo.setPoids(decoupage.getPoids());
		Set<Integer> id_decoupage = new HashSet<>();
		decoupage.getIndicateurs().forEach(indicateur ->{
			id_decoupage.add(indicateur.getId());
		});
		decoupagepojo.setIndicateurs(id_decoupage);
		return decoupagepojo;
		
	}
	
	@GetMapping("/getfils/{id}")
	public Set<Integer> getfils(@PathVariable(value = "id") Integer id){
		return decoupageservice.getFils(id);
	}
	
}
