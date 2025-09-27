package com.kd.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kd.dto.PurchaseOrderDTO;
import com.kd.model.Inventory;
import com.kd.model.OrderStatus;
import com.kd.model.Product;
import com.kd.model.PurchaseOrder;
import com.kd.model.PurchaseOrderItem;
import com.kd.model.Supplier;
import com.kd.repository.InventoryRepository;
import com.kd.repository.PurchaseOrderRepository;
import com.kd.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImple implements PurchaseOrderService {

	private final PurchaseOrderRepository purchaseOrderRepository;
	private final InventoryRepository inventoryRepository;
	private final SupplierRepository supplierRepository;
	private final ModelMapper modelMapper;
	private final AuditLogService auditLogService;

	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (authentication != null && authentication.isAuthenticated()) ? authentication.getName() : "System";
	}

	@Transactional
	@Override
	public PurchaseOrderDTO createOrder(PurchaseOrderDTO dto) {
		Supplier supplier = supplierRepository.findById(dto.getSupplierId())
				.orElseThrow(() -> new RuntimeException("Supplier not found"));

		// Step 1: create order without items
		PurchaseOrder order = PurchaseOrder.builder().supplier(supplier).status(OrderStatus.PENDING)
				.createdBy(dto.getCreatedBy()).createdAt(LocalDateTime.now()).build();

		// Step 2: create purchase order items
		List<PurchaseOrderItem> items = dto.getItems().stream().map(itemDto -> {
			Inventory inventory = inventoryRepository.findById(itemDto.getProductId())
					.orElseThrow(() -> new RuntimeException("Inventory not found"));
			Product product = inventory.getProduct();
			return PurchaseOrderItem.builder().product(product).quantityOrdered(itemDto.getQuantityOrdered())
					.expectedDeliveryDate(itemDto.getExpectedDeliveryDate()).purchaseOrder(order) // now order is
																									// initialized
					.build();
		}).collect(Collectors.toList());

		// Step 3: attach items to order
		order.setPurchaseOrderItems(items);

		// Step 4: save order
		PurchaseOrder purchaseOrder = purchaseOrderRepository.save(order);
		auditLogService.logAction("PurchaseOrder", purchaseOrder.getId(), "CREATE", getUsername());
		// Step 5: map to DTO
		return modelMapper.map(purchaseOrder, PurchaseOrderDTO.class);
	}

	@Transactional
	@Override
	public PurchaseOrderDTO updateStatus(Long id, OrderStatus orderStatus) {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not available"));
		purchaseOrder.setStatus(orderStatus);
		purchaseOrder.setUpdatedAt(LocalDateTime.now());
		PurchaseOrder orderStatusUpdated = purchaseOrderRepository.save(purchaseOrder);
		auditLogService.logAction("PurchaseOrder", orderStatusUpdated.getId(), "UPDATE", getUsername());
		return modelMapper.map(orderStatusUpdated, PurchaseOrderDTO.class);
	}

	@Transactional
	@Override
	public PurchaseOrderDTO receiveOrder(Long orderId) {
		PurchaseOrder order = purchaseOrderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found"));

		if (order.getStatus() != OrderStatus.APPROVED) {
			throw new RuntimeException("Order must be approved before receiving");
		}

		order.getPurchaseOrderItems().forEach(item -> {
			Inventory inventory = inventoryRepository.findById(item.getProduct().getId())
					.orElseThrow(() -> new RuntimeException("Inventory not found"));
			inventory.setQuantity(inventory.getQuantity() + item.getQuantityOrdered());
			inventoryRepository.save(inventory);
		});
		order.setStatus(OrderStatus.RECEIVED);
		order.setUpdatedAt(LocalDateTime.now());
		auditLogService.logAction("PurchaseOrder", order.getId(), "RECEIVE", getUsername());
		return modelMapper.map(purchaseOrderRepository.save(order), PurchaseOrderDTO.class);
	}

	@Override
	public void deleteOrder(Long id) {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Purchase Order id is " + id + " not found..!"));
             purchaseOrderRepository.delete(purchaseOrder);
             auditLogService.logAction("PurchaseOrder", purchaseOrder.getId(), "DELETE", getUsername());     
	}

}
