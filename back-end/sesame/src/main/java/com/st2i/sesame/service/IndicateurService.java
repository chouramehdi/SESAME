package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.Indicateur;

public interface IndicateurService {
	
	void addIndicateur(Indicateur Indicateur);
	
	Indicateur findOne(Integer id);
	
	List<Indicateur> getAll();
	
	void delete(Integer id);
}
