package com.SmartContracts.upc.smartcontract.repository;

import com.SmartContracts.upc.smartcontract.model.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, String> {
}
