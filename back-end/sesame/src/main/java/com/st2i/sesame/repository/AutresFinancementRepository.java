package com.st2i.sesame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st2i.sesame.entities.AutresFinancement;

public interface AutresFinancementRepository extends JpaRepository<AutresFinancement, Integer>{
	AutresFinancement findByDesignation(String designation);
}
