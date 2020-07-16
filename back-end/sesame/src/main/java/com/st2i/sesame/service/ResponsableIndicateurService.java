package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.ResponsableIndicateur;

public interface ResponsableIndicateurService {
	
	void addResponsableIndicateur(ResponsableIndicateur Responsable);

	ResponsableIndicateur findOne(Integer id);

	List<ResponsableIndicateur> getAll();

	void delete(Integer id);
}
