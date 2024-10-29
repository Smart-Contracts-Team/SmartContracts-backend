package com.SmartContracts.upc.user.service;

import com.SmartContracts.upc.user.model.User;
import com.SmartContracts.upc.user.model.UserDto;

import java.util.List;

public interface UserService {

    public abstract User createUser(User user);
    public abstract User getUserById(Long id);
    public abstract User updateUser(User user);
    public abstract void deleteUser(Long id);
    public abstract List<User> getAllUsers();
    public void existsUserById(Long Id);
    public void validateUser(UserDto user);
    public void existsUserByEmail(String email);
}
