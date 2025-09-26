package com.kd.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InventoryDTO {

	private Long id;
	private Integer quantity;
	private String location;
	private LocalDateTime lastupdated;
}
