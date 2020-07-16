package com.st2i.sesame.service;

import java.util.List;

import com.st2i.sesame.entities.TypeDecoupage;

public interface TypeDecoupageService {
	
	void addTypeDecoupage(TypeDecoupage type);

	TypeDecoupage findOne(Integer id);

	List<TypeDecoupage> getAll();

	void delete(Integer id);
}
