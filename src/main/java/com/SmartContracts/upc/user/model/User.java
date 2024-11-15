package com.SmartContracts.upc.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name="first_name", nullable = true, length = 100)
    private String firstName;

    @Column(name="last_name", nullable = true, length = 100)
    private String lastName;

    @Column(name="type_of_user", nullable = false, length = 100)
    private String typeOfUser;

    @Column(name="category", nullable = true, length = 100)
    private String category;

    @Column(name="ruc", nullable = true)
    private String ruc;

    @Column(name="email", nullable = false, length = 200)
    private String email;

    @Column(name="password", nullable = false, length = 100)
    private String password;

    @Column(name="phone", nullable = true, length = 20)
    private String phone;

    @Column(name="birth_date", nullable = true)
    private LocalDate birthDate;

    @Column(name="photo", nullable = true)
    private String photo;

    @Column(name="location", nullable = true)
    private String location;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
