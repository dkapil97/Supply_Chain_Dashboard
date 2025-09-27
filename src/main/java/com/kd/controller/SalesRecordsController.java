package com.kd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.dto.SalesRecordsDTO;
import com.kd.service.SalesRecordsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesRecordsController {

	private final SalesRecordsService salesRecordsService;
	
	@GetMapping("/")
	public ResponseEntity<List<SalesRecordsDTO>> findAllSalesRecords(){
		List<SalesRecordsDTO> allSalesRecords = salesRecordsService.getAllSalesRecords();
		return new ResponseEntity<>(allSalesRecords,HttpStatus.OK);
	}

}
