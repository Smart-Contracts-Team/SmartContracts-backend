package com.SmartContracts.upc.smartcontract.repository;

import com.SmartContracts.upc.smartcontract.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
