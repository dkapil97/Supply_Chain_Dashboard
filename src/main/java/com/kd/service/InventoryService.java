package com.kd.service;

import java.util.List;

import com.kd.dto.InventoryDTO;

public interface InventoryService {

	List<InventoryDTO> getAllInventory();

	InventoryDTO createInventory(InventoryDTO inventoryDTO);
	
	InventoryDTO getInventoryById(Long id);
	
	InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO);
	
	void deleteInventory(Long id);
}
