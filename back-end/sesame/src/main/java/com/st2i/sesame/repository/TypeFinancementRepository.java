package com.st2i.sesame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st2i.sesame.entities.TypeFinancement;

public interface TypeFinancementRepository extends JpaRepository<TypeFinancement, Integer>{
	TypeFinancement findByDesignation(String designation);
}
