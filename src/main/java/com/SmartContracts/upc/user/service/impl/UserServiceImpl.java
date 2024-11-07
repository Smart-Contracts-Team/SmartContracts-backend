package com.SmartContracts.upc.user.service.impl;

import com.SmartContracts.upc.exception.ValidationException;
import com.SmartContracts.upc.user.model.User;
import com.SmartContracts.upc.user.model.UserDto;
import com.SmartContracts.upc.user.repository.UserRepository;
import com.SmartContracts.upc.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = getUserById(user.getId());
        if(existingUser != null){
            User userToUpdate = existingUser;
            userToUpdate.setUser_name(user.getUser_name());
            userToUpdate.setRuc(user.getRuc());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setTypeOfUser(user.getTypeOfUser());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setPhoto(user.getPhoto());
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setLocation(user.getLocation());
            userToUpdate.setRole(user.getRole());
            return userRepository.save(userToUpdate);
        }else{
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void existsUserById(Long Id) {
        if (!userRepository.existsById(Id)) {
            throw new ValidationException("No existe ningún usuario");
        }
    }

    @Override
    public void validateUser(UserDto user) {
        if(user == null){
            throw new ValidationException("El usuario es nulo");
        }
        if(user.getTypeOfUser() == null || user.getTypeOfUser().isEmpty()){
            throw new ValidationException("El usuario necesita especificar su tipo");
        }
        if(user.getTypeOfUser().length()>100) {
            throw new ValidationException("El tipo de usuario es demasiado largo");
        }
        if(user.getFirstName() == null || user.getFirstName().isEmpty()){
            throw new ValidationException("El nombre del usuario debe ser obligatorio");
        }
        if(user.getFirstName().length()>100) {
            throw new ValidationException("El nombre del usuario no debe exceder los 200 caracteres");
        }
        if(user.getLastName()==null || user.getLastName().isEmpty()) {
            throw new ValidationException("El apellido del usuario debe ser obligatorio");
        }
        if(user.getLastName().length()>100) {
            throw new ValidationException("El apellido del usuario no debe exceder los 200 caracteres");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("El email del usuario debe ser obligatorio");
        }
        if (user.getEmail().length() > 200) {
            throw new ValidationException("El email del usuario no debe exceder los 300 caracteres");
        }

    }

    @Override
    public void existsUserByEmail(String email){
        if (userRepository.existsByEmail(email)) {
            throw new ValidationException("Ya existe un usuario con el email " + email);
        }
    }
}
