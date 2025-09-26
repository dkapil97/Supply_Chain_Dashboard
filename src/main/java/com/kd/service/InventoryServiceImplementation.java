package com.kd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kd.dto.InventoryDTO;
import com.kd.exception.ResourceNotFoundException;
import com.kd.model.Inventory;
import com.kd.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImplementation implements InventoryService {

	private final InventoryRepository inventoryRepository;

	private final ModelMapper modelMapper;

	@Override
	public List<InventoryDTO> getAllInventory() {
		// TODO Auto-generated method stub
		return inventoryRepository.findAll().stream().map(this::convertDto).collect(Collectors.toList());
	}

	@Override
	public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
		Inventory inventory = convertEntity(inventoryDTO);
		Inventory inventory2 = inventoryRepository.save(inventory);
		return convertDto(inventory2);
	}

	@Override
	public InventoryDTO getInventoryById(Long id) {
		// TODO Auto-generated method stub
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory is not available with::" + id));
		return convertDto(inventory);
	}

	@Override
	public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
		Inventory existingInventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory is not available with::" + id));
		existingInventory.setQuantity(inventoryDTO.getQuantity());
		existingInventory.setLocation(inventoryDTO.getLocation());
		existingInventory.setLastupdated(inventoryDTO.getLastupdated());
		Inventory updatedInventory = inventoryRepository.save(existingInventory);

		return convertDto(updatedInventory);
	}

	@Override
	public void deleteInventory(Long id) {
		Inventory existingInventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory is not available with::" + id));
		inventoryRepository.delete(existingInventory);

	}

	// helper
	private InventoryDTO convertDto(Inventory inventory) {
		InventoryDTO inventoryDTO = modelMapper.map(inventory, InventoryDTO.class);
		return inventoryDTO;
	}

	private Inventory convertEntity(InventoryDTO inventoryDTO) {
		return modelMapper.map(inventoryDTO, Inventory.class);
	}

}
