package com.kd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kd.model.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>{

}
