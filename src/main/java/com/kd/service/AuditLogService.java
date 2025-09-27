package com.kd.service;

import java.util.List;

import com.kd.dto.AuditLogDTO;

public interface AuditLogService {

	void logAction(String entityType, Long entityId, String action, String performedBy);

	List<AuditLogDTO> getLogsForEntity(String entityType, Long entityId);

	List<AuditLogDTO> getAllLogs();
}
