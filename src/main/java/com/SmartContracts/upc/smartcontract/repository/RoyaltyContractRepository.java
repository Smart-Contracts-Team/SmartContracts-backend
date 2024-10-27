package com.SmartContracts.upc.smartcontract.repository;

import com.SmartContracts.upc.smartcontract.model.RoyaltyContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoyaltyContractRepository extends JpaRepository<RoyaltyContract, String>{
}
