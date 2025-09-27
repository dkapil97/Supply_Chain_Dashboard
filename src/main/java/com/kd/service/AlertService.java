package com.kd.service;

import java.util.List;

import com.kd.dto.AlertDTO;

public interface AlertService {

	public void checkAllStocksAlerts();
	
	public List<AlertDTO> getActiveAlerts();
	
	public void resolve(Long alertId);
}
