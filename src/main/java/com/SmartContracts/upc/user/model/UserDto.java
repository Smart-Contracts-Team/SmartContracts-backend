package com.SmartContracts.upc.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String typeOfUser;
    private String ruc;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String photo;
    private String location;
    private Role role;
}
