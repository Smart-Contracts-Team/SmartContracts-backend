package com.SmartContracts.upc.user.repository;

import com.SmartContracts.upc.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    List<User> findAll();
    Optional<User> findByEmail(String email);

}
