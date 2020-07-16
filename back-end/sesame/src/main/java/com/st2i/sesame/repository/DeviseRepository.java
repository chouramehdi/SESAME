package com.st2i.sesame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st2i.sesame.entities.Devise;

public interface DeviseRepository extends JpaRepository<Devise, Integer>{
	Devise findByDesignation(String designation);
}
