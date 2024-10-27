package com.SmartContracts.upc.smartcontract.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private String id;
    private String contractId;
    private String transactionHash;
    private String status;
    private BigDecimal amount;
    private String timestamp;
}
