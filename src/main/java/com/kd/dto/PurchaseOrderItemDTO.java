package com.kd.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderItemDTO {
	private Long productId;
    private String productName;
    private int quantityOrdered;
    private LocalDate expectedDeliveryDate;
}
