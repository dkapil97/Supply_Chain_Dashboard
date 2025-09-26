package com.kd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kd.model.SalesRecords;
@Repository
public interface SalesRecordsRepository extends JpaRepository<SalesRecords, Long>{

}
