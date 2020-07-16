package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.Devise;

public interface DeviseService {
	
	void addDevise(Devise Devise);
	
	Devise findOne(Integer id);
	
	List<Devise> getAll();
	
	void delete(Integer id);
	
	Devise findByDesignation(String designation);
}
