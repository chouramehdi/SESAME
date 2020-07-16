package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.Bailleur;

public interface BailleurService {
	
	void addBailleur(Bailleur bailleur);
	
	Bailleur findOne(Integer id);
	
	List<Bailleur> getAll();
	
	void delete(Integer id);
	
	Bailleur findByDesignation(String designation);
}
