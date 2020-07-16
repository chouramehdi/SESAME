package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.NatureFinancement;

public interface NatureFinancementService {
	
void addNatureFinancement(NatureFinancement NatureFinancement);
	
	NatureFinancement findOne(Integer id);
	
	List<NatureFinancement> getAll();
	
	void delete(Integer id);
	
	NatureFinancement findByDesignation(String designation);
}
