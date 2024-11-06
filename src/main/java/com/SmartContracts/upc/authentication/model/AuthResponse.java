package com.SmartContracts.upc.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private long id;
    private String token;
    private boolean success;
    private String typeOfUser;
}
