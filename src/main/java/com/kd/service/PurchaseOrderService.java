package com.kd.service;

import com.kd.dto.PurchaseOrderDTO;
import com.kd.model.OrderStatus;

public interface PurchaseOrderService {

	public PurchaseOrderDTO createOrder(PurchaseOrderDTO purchaseOrderDTO);

	public PurchaseOrderDTO updateStatus(Long id, OrderStatus orderStatus);

	public PurchaseOrderDTO receiveOrder(Long orderId);
	
	public void deleteOrder(Long id);
	
}
