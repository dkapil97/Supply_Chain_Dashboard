package com.kd.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SalesRecordsDTO {

	private Long id;
    private String productName;
    private Integer quantity;
    private LocalDateTime saleDate;
    private String customer;
}
