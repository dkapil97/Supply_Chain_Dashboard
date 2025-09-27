package com.kd.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
public class AlertDTO {
	private Long id;
    private String type;
    private String productName;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}
