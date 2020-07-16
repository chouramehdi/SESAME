package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.TypeIndicateur;

public interface TypeIndicateurService {
	
	void addTypeIndicateur(TypeIndicateur Type);

	TypeIndicateur findOne(Integer id);

	List<TypeIndicateur> getAll();

	void delete(Integer id);
}
