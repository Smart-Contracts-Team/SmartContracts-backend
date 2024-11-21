package com.SmartContracts.upc.smartcontract.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmartContractResponse {
    private String transactionHash;
    private String status;
    private BigInteger contractId;
    private BigInteger influencerId;
    private BigInteger businessId;

}
