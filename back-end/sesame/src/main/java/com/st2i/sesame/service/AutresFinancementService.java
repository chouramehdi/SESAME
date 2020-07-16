package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.AutresFinancement;

public interface AutresFinancementService {
	
	void addAutresFinancement(AutresFinancement autrefinancement);
	
	AutresFinancement findOne(Integer id);
	
	List<AutresFinancement> getAll();
	
	void delete(Integer id);
	
	AutresFinancement findByDesignation(String designation);
}
