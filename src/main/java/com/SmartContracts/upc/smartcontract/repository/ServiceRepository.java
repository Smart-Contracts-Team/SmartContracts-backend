package com.SmartContracts.upc.smartcontract.repository;

import com.SmartContracts.upc.smartcontract.model.ServiceU;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceU,Long> {
    List<ServiceU> findByUserId(Long userId);
    List<ServiceU> findByCategory(String category);
}
