package com.st2i.sesame.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st2i.sesame.entities.AuthEventLogHistory;
import com.st2i.sesame.repository.AuthEventLogRepository;
import com.st2i.sesame.service.AuthEventLogService;

@Service
public class AuthEventLogImpl implements AuthEventLogService {
	@Autowired
	AuthEventLogRepository authRepo;

	@Override
	public AuthEventLogHistory addEvent(AuthEventLogHistory event) {
		return authRepo.save(event);
	}

	@Override
	public List<AuthEventLogHistory> getHistory() {
		return authRepo.findAll();
	}

}
