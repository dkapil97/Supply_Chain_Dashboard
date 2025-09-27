package com.kd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kd.dto.SalesRecordsDTO;
import com.kd.model.SalesRecords;
import com.kd.repository.SalesRecordsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesRecordsServiceImple implements SalesRecordsService{

	private final SalesRecordsRepository salesRecordsRepository;
	private final ModelMapper modelMapper;
	private final AuditLogService auditLogService;
	
	private String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (auth!=null && auth.isAuthenticated())?auth.getName():"SYSTEM";
	}
	@Override
	public List<SalesRecordsDTO> getAllSalesRecords() {
		// TODO Auto-generated method stub
		return salesRecordsRepository.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
	}

	private SalesRecordsDTO convertDTO(SalesRecords salesRecords) {
		SalesRecordsDTO salesRecordsDTO = modelMapper.map(salesRecords, SalesRecordsDTO.class);
		salesRecordsDTO.setProductName(salesRecords.getProduct().getName());
		return salesRecordsDTO;
	}
	private SalesRecords convertToEntity(SalesRecordsDTO salesRecordsDTO) {
		return modelMapper.map(salesRecordsDTO, SalesRecords.class);
	}
}
