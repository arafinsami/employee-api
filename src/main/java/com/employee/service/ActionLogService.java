package com.employee.service;

import org.springframework.stereotype.Service;

import com.employee.entity.Action;
import com.employee.entity.ActionLog;
import com.employee.entity.ModuleName;
import com.employee.repository.ActionLogRepository;
import com.employee.utils.WebUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActionLogService {

	private final ActionLogRepository repository;

	public ActionLog publishActivity(Action action, ModuleName name, Long documentId,String comments) {

		ActionLog log = new ActionLog();
		log.setAction(action);
		log.setModuleName(name);
		log.setDocumentId(documentId);
		log.setIpAddress(WebUtils.getCurrentRequest().getRequestURI());
		log.setComments(comments);
		repository.save(log);
		return null;
	}
}
