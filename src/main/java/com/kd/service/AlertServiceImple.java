package com.kd.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kd.dto.AlertDTO;
import com.kd.model.Alert;
import com.kd.model.AlertStatus;
import com.kd.model.AlertType;
import com.kd.model.Product;
import com.kd.repository.AlertRepository;
import com.kd.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertServiceImple implements AlertService {
	
	private final AlertRepository alertRepository;
	private final InventoryRepository inventoryRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public void checkAllStocksAlerts() {
		inventoryRepository.findAll().forEach(inventory ->{
			if(inventory.getQuantity()<=0) {
				saveAlert(AlertType.OUT_OF_STOCK,inventory.getProduct(),"Out of Stock !");
			}else if(inventory.getQuantity()<inventory.getThreshold()){
				saveAlert(AlertType.LOW_STOCK, inventory.getProduct(), "Below threshold!");
			}
		});
	}

	private void saveAlert(AlertType outOfStock, Product product, String string) {
		// TODO Auto-generated method stub
		Alert alert = Alert.builder()
		.alertType(outOfStock)
		.product(product)
		.message(string)
		.status(AlertStatus.ACTIVE)
		.createdAt(LocalDateTime.now())
		.build();
		alertRepository.save(alert);
	}

	@Override
	public List<AlertDTO> getActiveAlerts() {
		return alertRepository.findAll().stream()
				.filter(a->a.getStatus()==AlertStatus.ACTIVE)
				.map(a->{
					AlertDTO alert = modelMapper.map(a, AlertDTO.class);
					if(a.getProduct()!=null) alert.setProductName(a.getProduct().getName());
					return alert;
				}).collect(Collectors.toList());
	}

	@Override
	public void resolve(Long alertId) {
		Alert alert = alertRepository.findById(alertId).orElseThrow(()-> new RuntimeException("Alert not Found"));
		alert.setStatus(AlertStatus.RESOLVED);
		alert.setResolvedAt(LocalDateTime.now());
		alertRepository.save(alert);
		
	}

}
