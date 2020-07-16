package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.SecteurDecoupage;

public interface SecteurDecoupageService {
	
	void addSecteurDecoupage(SecteurDecoupage Secteur);

	SecteurDecoupage findOne(Integer id);

	List<SecteurDecoupage> getAll();

	void delete(Integer id);
}
