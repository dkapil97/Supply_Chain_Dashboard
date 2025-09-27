package com.kd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kd.dto.PurchaseOrderDTO;
import com.kd.model.OrderStatus;
import com.kd.service.PurchaseOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

	private final PurchaseOrderService purchaseOrderService;
	
	@PostMapping
	public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(PurchaseOrderDTO dto){
		 PurchaseOrderDTO order = purchaseOrderService.createOrder(dto);
		 return new ResponseEntity<PurchaseOrderDTO>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/orderStatus")
	public ResponseEntity<PurchaseOrderDTO> updateOrderStatus(@PathVariable Long id,@RequestParam OrderStatus orderStatus) {
		PurchaseOrderDTO updateStatus = purchaseOrderService.updateStatus(id, orderStatus);
		return new ResponseEntity<PurchaseOrderDTO>(updateStatus,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PurchaseOrderDTO> receiveOrder(@PathVariable Long id) {
		PurchaseOrderDTO receiveOrder = purchaseOrderService.receiveOrder(id);
		return new ResponseEntity<PurchaseOrderDTO>(receiveOrder,HttpStatus.OK);
	}
}
