package com.SmartContracts.upc.smartcontract.service;

import com.SmartContracts.upc.smartcontract.model.RoyaltyContract;
import com.SmartContracts.upc.smartcontract.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ContractService {
    RoyaltyContract deployContract(String creatorId, String contractType);
    Transaction transferRoyalty(String contractId, String toAddress, BigDecimal amount);
    List<Transaction> getTransactions(String contractId);
}
