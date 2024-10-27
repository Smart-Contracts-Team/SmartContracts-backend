package com.SmartContracts.upc.smartcontract.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoyaltyContract {
    @Id
    private String id;
    private String contractAddress;
    private String contractType;
    private String creatorId;
    private String description;
    private Date deadline;
}
