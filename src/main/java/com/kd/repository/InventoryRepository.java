package com.kd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kd.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

}
