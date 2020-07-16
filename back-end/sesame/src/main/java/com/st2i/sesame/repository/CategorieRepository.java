package com.st2i.sesame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st2i.sesame.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie,Integer>{
	
	Categorie findByDesignation(String designation);
}
