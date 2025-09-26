package com.kd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.dto.SupplierDTO;
import com.kd.service.SupplierService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

	private final SupplierService supplierService;
	
	@GetMapping("/")
	public ResponseEntity<List<SupplierDTO>> getAllSupplierDetail(){
		List<SupplierDTO> allSupplier = supplierService.getAllSupplier();
		return new ResponseEntity<>(allSupplier,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id){
		SupplierDTO supplierById = supplierService.getSupplierById(id);
		return new ResponseEntity<SupplierDTO>(supplierById,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO){
		SupplierDTO supplier = supplierService.createSupplier(supplierDTO);
		return new ResponseEntity<SupplierDTO>(supplier,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SupplierDTO> updatedSupplier(@PathVariable Long id,@RequestBody SupplierDTO supplierDTO){
		SupplierDTO updateSupplier = supplierService.updateSupplier(id, supplierDTO);
		return new ResponseEntity<SupplierDTO>(updateSupplier,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
		supplierService.deleteSupplierById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
