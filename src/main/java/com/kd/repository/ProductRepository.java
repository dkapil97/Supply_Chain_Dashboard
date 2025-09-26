package com.kd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kd.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
