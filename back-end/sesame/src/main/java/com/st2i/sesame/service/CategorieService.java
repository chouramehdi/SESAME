package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.Categorie;

public interface CategorieService {
	
	void addCategorie(Categorie cathegorie);
	
	Categorie findOne(Integer id);
	
	List<Categorie> getAll();
	
	void delete(Integer id);
	
	Categorie findByDesignation(String designation);
}
