package com.kd.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantity;
	private String location;
	private LocalDateTime lastupdated;
	private int threshold;
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public boolean isLowStock() {
        return quantity < 10; // example threshold
    }

	public Integer getThreshold() {
		// TODO Auto-generated method stub
		return threshold;
	}
}
