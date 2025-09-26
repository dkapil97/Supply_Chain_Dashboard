package com.kd.service;

import java.util.List;

import com.kd.dto.SupplierDTO;

public interface SupplierService {

	List<SupplierDTO> getAllSupplier();
	
	
	SupplierDTO createSupplier(SupplierDTO supplierDTO);
	
	SupplierDTO getSupplierById(Long id);
	
	SupplierDTO updateSupplier(Long id,SupplierDTO supplierDTO);
	
	void deleteSupplierById(Long id);
}
