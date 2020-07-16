package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.TypeFinancement;


public interface TypeFinancementService {
	
	void addTypeFinancement(TypeFinancement type);
	
	TypeFinancement findOne(Integer id);
	
	List<TypeFinancement> getAll();
	
	void delete(Integer id);
	
	TypeFinancement findByDesignation(String designation);
}
