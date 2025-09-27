package com.kd.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Supplier supplier;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private String createdBy;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "purchaseOrder",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<PurchaseOrderItem> purchaseOrderItems;
}
