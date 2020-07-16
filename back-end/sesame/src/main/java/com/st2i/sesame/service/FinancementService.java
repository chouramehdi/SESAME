package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.Financement;

public interface FinancementService {
	
	void addFinancement(Financement Financement);
	
	Financement findOne(Integer id);
	
	List<Financement> getAll();
	
	void delete(Integer id);
}
