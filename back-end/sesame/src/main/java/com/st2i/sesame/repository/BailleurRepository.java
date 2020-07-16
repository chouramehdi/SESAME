package com.st2i.sesame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st2i.sesame.entities.Bailleur;

public interface BailleurRepository extends JpaRepository<Bailleur,Integer>{
	Bailleur findByDesignation(String designation);
}
