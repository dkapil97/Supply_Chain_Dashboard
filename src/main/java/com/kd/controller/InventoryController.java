package com.kd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.dto.InventoryDTO;
import com.kd.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;
	
	@GetMapping("/")
	public ResponseEntity<List<InventoryDTO>> getAllInventory(){
		List<InventoryDTO> allInventory = inventoryService.getAllInventory();
		return new ResponseEntity<List<InventoryDTO>>(allInventory,HttpStatus.OK);
	}
}
