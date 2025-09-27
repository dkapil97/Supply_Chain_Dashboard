package com.kd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.dto.AlertDTO;
import com.kd.service.AlertService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

	private final AlertService alertService;
	
	@GetMapping("/")
	public ResponseEntity<List<AlertDTO>> getActiveAlerts(){
		List<AlertDTO> activeAlerts = alertService.getActiveAlerts();
		return new ResponseEntity<List<AlertDTO>>(activeAlerts,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/resolve")
	public void resolveAlerts(@PathVariable Long id) {
		alertService.resolve(id);
	}
}
