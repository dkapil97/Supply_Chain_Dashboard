package com.kd.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kd.dto.SupplierDTO;
import com.kd.exception.ResourceNotFoundException;
import com.kd.model.Supplier;
import com.kd.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImple implements SupplierService {

	private final SupplierRepository supplierRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<SupplierDTO> getAllSupplier() {
		// TODO Auto-generated method stub
		return supplierRepository.findAll().stream().map(this::convertDTO).toList();
	}

	@Override
	public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
		Supplier supplier = convertToEntity(supplierDTO);
		Supplier supplier2 = supplierRepository.save(supplier);
		return convertDTO(supplier2);
	}

	@Override
	public SupplierDTO getSupplierById(Long id) {
		Supplier supplier = supplierRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier is not found with::" + id));
		return convertDTO(supplier);
	}

	@Override
	public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
		Supplier existingSupplier = supplierRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Supplier is not available with::"+id));
		existingSupplier.setName(supplierDTO.getName());
		existingSupplier.setContactEmail(supplierDTO.getContactEmail());
		Supplier updatedSupplier = supplierRepository.save(existingSupplier);
		return convertDTO(updatedSupplier);
	}

	@Override
	public void deleteSupplierById(Long id) {
		// TODO Auto-generated method stub
		Supplier supplier = supplierRepository.findById(id)
		.orElseThrow(()->new ResourceNotFoundException("Supplier is not available::"+id));
		supplierRepository.delete(supplier);

	}

	// convert SupplierDto to supplier class
	private SupplierDTO convertDTO(Supplier supplier) {
		SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);
		supplierDTO.setTotalProducts(supplier.getProducts() != null ? supplier.getProducts().size() : 0);
		return supplierDTO;
	}

	private Supplier convertToEntity(SupplierDTO supplierDTO) {
		return modelMapper.map(supplierDTO, Supplier.class);
	}

}
