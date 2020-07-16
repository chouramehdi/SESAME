package com.st2i.sesame.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st2i.sesame.entities.GedFile;
import com.st2i.sesame.repository.GedFileRepository;
import com.st2i.sesame.service.GedFileService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GedController {
	@Autowired GedFileService gedservice;
	@Autowired GedFileRepository gedRepo;
	String ticket;

	@GetMapping("/gedfile/{id}")
	public ResponseEntity<GedFile> getBailleurById(@PathVariable(value = "id") Integer id){
		GedFile file = new GedFile();
		file = gedRepo.findById(id).get();
		return ResponseEntity.ok().body(file);
	}
	@PostMapping ("/gedfile")
	public GedFile addFile(@Valid @RequestBody GedFile file) {
		gedservice.addGedFile(file);
		return file;
	}
	@GetMapping("/gedfile")
	List<GedFile>getAllFiles(){
		return gedRepo.findAll();
	}
}
