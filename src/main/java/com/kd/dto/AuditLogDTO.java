package com.kd.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AuditLogDTO {
	private Long id;
	private String entityType; // product,supplier,inventory,order
	private Long entityId; // entity id
	private String action; // create,update and delete, approve
	private String performedBy; // username/role
	private LocalDateTime dateTime;
}
