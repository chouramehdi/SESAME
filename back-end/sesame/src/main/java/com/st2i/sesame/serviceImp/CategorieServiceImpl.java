package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.Categorie;
import com.st2i.sesame.repository.CategorieRepository;
import com.st2i.sesame.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService{
	
	@Autowired CategorieRepository cathegorieRepo;
	
	@Override
	public void addCategorie(Categorie categorie) {
		cathegorieRepo.save(categorie);
	}

	@Override
	public Categorie findOne(Integer id) {
		return cathegorieRepo.findById(id).get();
	}

	@Override
	public List<Categorie> getAll() {
		
		return cathegorieRepo.findAll();
	}

	@Override
	public void delete(Integer id) {
		cathegorieRepo.deleteById(id);
	}

	@Override
	public Categorie findByDesignation(String designation) {
		return cathegorieRepo.findByDesignation(designation);
	}

}
