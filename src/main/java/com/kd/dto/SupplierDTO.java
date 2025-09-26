package com.kd.dto;

import lombok.Data;

@Data
public class SupplierDTO {

	private Long id;
	private String name;
	private String contactEmail;
	private Integer totalProducts;
}
