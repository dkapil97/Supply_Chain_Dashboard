package com.kd.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.dto.AuditLogDTO;
import com.kd.service.AuditLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditLogController {

	private final AuditLogService auditLogService;
	
	@GetMapping("/")
	public ResponseEntity<List<AuditLogDTO>> getAllAuditLog(){
		return ResponseEntity.ok(auditLogService.getAllLogs());
	}
	@GetMapping("/{entityType}/{entityId}")
	public ResponseEntity<List<AuditLogDTO>> getEntityAuditLog(@PathVariable String entityType,@PathVariable Long entityId){
		return ResponseEntity.ok(auditLogService.getLogsForEntity(entityType, entityId));
	}
}
