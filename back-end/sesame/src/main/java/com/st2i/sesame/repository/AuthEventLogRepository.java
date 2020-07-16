package com.st2i.sesame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st2i.sesame.entities.AuthEventLogHistory;

public interface AuthEventLogRepository extends JpaRepository<AuthEventLogHistory, Long> {
	
}
