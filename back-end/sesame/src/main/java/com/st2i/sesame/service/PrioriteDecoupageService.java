package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.PrioriteDecoupage;

public interface PrioriteDecoupageService {
	
	void addPrioriteDecoupage(PrioriteDecoupage Priorite);

	PrioriteDecoupage findOne(Integer id);

	List<PrioriteDecoupage> getAll();

	void delete(Integer id);
}
