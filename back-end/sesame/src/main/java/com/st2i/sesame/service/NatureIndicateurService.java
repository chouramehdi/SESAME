package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.NatureIndicateur;

public interface NatureIndicateurService {
	void addNatureIndicateur(NatureIndicateur NatureIndicateur);
	
	NatureIndicateur findOne(Integer id);
	
	List<NatureIndicateur> getAll();
	
	void delete(Integer id);
}
