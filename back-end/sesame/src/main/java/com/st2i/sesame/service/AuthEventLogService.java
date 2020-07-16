package com.st2i.sesame.service;

import java.util.List;
import com.st2i.sesame.entities.AuthEventLogHistory;

public interface AuthEventLogService {
	List<AuthEventLogHistory> getHistory();
	AuthEventLogHistory addEvent(AuthEventLogHistory event);
}
