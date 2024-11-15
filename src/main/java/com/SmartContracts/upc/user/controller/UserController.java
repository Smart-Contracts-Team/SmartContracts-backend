package com.SmartContracts.upc.user.controller;

import com.SmartContracts.upc.user.model.User;
import com.SmartContracts.upc.user.model.UserDto;
import com.SmartContracts.upc.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/smartcontract/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // URL: http://localhost:8080/api/smartcontract/v1/user
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/user/{userId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "userId") Long userId) {
        userService.existsUserById(userId);
        User user = userService.getUserById(userId);
        UserDto userDto = convertToDto(user);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/smartcontract/v1/user/{userId}
    // Method: PUT
    @Transactional
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable(name="userId")Long userId,@RequestBody UserDto user) {
        userService.existsUserById(userId);
        userService.validateUser(user);

        User responseUser = new User();
        responseUser.setId(userId);
        responseUser.setUser_name(user.getUser_name());
        responseUser.setRuc(user.getRuc());
        responseUser.setFirstName(user.getFirstName());
        responseUser.setLastName(user.getLastName());
        responseUser.setTypeOfUser(user.getTypeOfUser());
        responseUser.setCategory(user.getCategory());
        responseUser.setEmail(user.getEmail());
        responseUser.setBirthDate(user.getBirthDate());
        responseUser.setPhoto(user.getPhoto());
        responseUser.setPhone(user.getPhone());
        responseUser.setLocation(user.getLocation());
        responseUser.setRole(user.getRole());

        User userSaved = userService.updateUser(responseUser);
        return new ResponseEntity<UserDto>(convertToDto(userSaved),HttpStatus.OK);
    }

    private UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .user_name(user.getUser_name())
                .ruc(user.getRuc())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .category(user.getCategory())
                .typeOfUser(user.getTypeOfUser())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .photo(user.getPhoto())
                .location(user.getLocation())
                .role(user.getRole())
                .build();
    }

}
