package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.NatureDecoupage;

public interface NatureDecoupageService {
	
	void addNatureDecoupage(NatureDecoupage NatureDecoupage);
	
	NatureDecoupage findOne(Integer id);
	
	List<NatureDecoupage> getAll();
	
	void delete(Integer id);
}
