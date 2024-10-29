package com.SmartContracts.upc.authentication.model;

import com.SmartContracts.upc.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String typeOfUser;
    private String ruc;
    private String email;
    private String password;
    private String phone;
    private LocalDate birthDate;
    private String photo;
    private String location;
    private Role role;
}
