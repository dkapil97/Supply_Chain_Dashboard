package com.kd.dto;



import java.util.List;

import com.kd.model.Inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String sku;
	private String category;
	private Double price;
	private String supplierName; 
	private Integer inventoryQuantity;
	private List<Inventory> inventories;
	

}
