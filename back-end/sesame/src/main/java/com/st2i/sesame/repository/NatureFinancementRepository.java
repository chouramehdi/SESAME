package com.st2i.sesame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st2i.sesame.entities.NatureFinancement;

public interface NatureFinancementRepository extends JpaRepository<NatureFinancement, Integer>{
	NatureFinancement findByDesignation(String designation);
}
