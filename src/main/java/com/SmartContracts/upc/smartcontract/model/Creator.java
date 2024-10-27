package com.SmartContracts.upc.smartcontract.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Creator {
    @Id
    private String id;
    private String name;
    private String walletAddress;
    private String contentType;
}
