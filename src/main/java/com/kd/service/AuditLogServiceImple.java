package com.kd.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kd.dto.AuditLogDTO;
import com.kd.model.AuditLog;
import com.kd.repository.AuditLogRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuditLogServiceImple implements AuditLogService{
	
	private final AuditLogRepository auditLogRepository;
	private final ModelMapper modelMapper;
	@Override
	public void logAction(String entityType, Long entityId, String action, String performedBy) {
		AuditLog auditLog = AuditLog.builder()
		.entityType(entityType)
		.entityId(entityId)
		.action(action)
		.performedBy(performedBy)
		.dateTime(LocalDateTime.now())
		.build();
		auditLogRepository.save(auditLog);
	}

	@Override
	public List<AuditLogDTO> getLogsForEntity(String entityType, Long entityId) {
		
		return auditLogRepository.findByEntityTypeAndEntityId(entityType, entityId)
				.stream()
				.map(log->modelMapper.map(log, AuditLogDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<AuditLogDTO> getAllLogs() {
		
		return auditLogRepository.findAll()
				.stream()
				.map(audit-> modelMapper.map(audit, AuditLogDTO.class))
				.toList();
	}

}
