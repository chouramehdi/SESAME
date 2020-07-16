package com.st2i.sesame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.st2i.sesame.entities.AuthEventLogHistory;
import com.st2i.sesame.service.AuthEventLogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthEventLogController {
	@Autowired AuthEventLogService authService;
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping ("/history")
	public List<AuthEventLogHistory> getListHistory(){
		return authService.getHistory();
	}
	
	
}
