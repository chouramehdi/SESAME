package com.st2i.sesame.service;

import java.util.List;
import java.util.Set;

import com.st2i.sesame.entities.DecoupageAnalytique;

public interface DecoupageAnalytiqueService {
	
	void addDecoupageAnalytique(DecoupageAnalytique DecoupageAnalytique);
	
	DecoupageAnalytique findOne(Integer id);
	
	List<DecoupageAnalytique> getAll();
	
	void delete(Integer id);
	
	Set<Integer> getFils(Integer id);
	
}
