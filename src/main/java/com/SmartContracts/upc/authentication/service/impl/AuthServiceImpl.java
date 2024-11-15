package com.SmartContracts.upc.authentication.service.impl;

import com.SmartContracts.upc.authentication.model.AuthResponse;
import com.SmartContracts.upc.authentication.model.LoginRequest;
import com.SmartContracts.upc.authentication.model.RegisterRequest;
import com.SmartContracts.upc.authentication.service.AuthService;
import com.SmartContracts.upc.exception.ValidationException;
import com.SmartContracts.upc.jwt.JwtService;
import com.SmartContracts.upc.user.model.Role;
import com.SmartContracts.upc.user.model.User;
import com.SmartContracts.upc.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService,
                           PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .user_name(registerRequest.getUser_name())
                .typeOfUser(registerRequest.getTypeOfUser())
                .category(registerRequest.getCategory())
                .email(registerRequest.getEmail())
                .ruc(registerRequest.getRuc())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .birthDate(registerRequest.getBirthDate())
                .phone(registerRequest.getPhone())
                .photo(registerRequest.getPhoto())
                .location(registerRequest.getLocation())
                .role(registerRequest.getRole())
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .id(user.getId())
                .success(true)
                .typeOfUser(user.getTypeOfUser())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        long id = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow().getId();
        User userLogged = userRepository.findById(id).orElse(null);
        return AuthResponse.builder()
                .token(token)
                .id(id)
                .success(true)
                .typeOfUser(userLogged.getTypeOfUser())
                .build();
    }

    @Override
    public void validateRegisterRequest(RegisterRequest registerRequest) {

        if(registerRequest == null){
            throw new ValidationException("El formato de registro no tiene que ser nulo.");
        }
        if(registerRequest.getUser_name() == null || registerRequest.getUser_name().isEmpty()){
            throw new ValidationException("El usuario necesita especificar su username");
        }
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new ValidationException("El email del usuario debe ser obligatorio");
        }
        if (registerRequest.getEmail().length() > 300) {
            throw new ValidationException("El email del usuario no debe exceder los 300 caracteres");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            throw new ValidationException("La contraseña del usuario debe ser obligatorio");
        }
        if (registerRequest.getPassword().length() > 100) {
            throw new ValidationException("La contraseña del usuario no debe exceder los 100 caracteres");
        }
        if(registerRequest.getRole() != Role.ADMIN && registerRequest.getRole() != Role.USER ){
            throw new ValidationException("Selecciona rol válido, ADMIN o USER");
        }
        if(registerRequest.getTypeOfUser()==null  ||
                registerRequest.getTypeOfUser().isEmpty())
        {
            throw new ValidationException("El usuario necesita especificar su tipo");
        }
        if (!"Influencer".equals(registerRequest.getTypeOfUser()) && !"Business".equals(registerRequest.getTypeOfUser())) {
            throw new ValidationException("El tipo de usuario aceptado es Influencer o Business");
        }

        if ("Influencer".equals(registerRequest.getTypeOfUser())){ //Validaciones para influencer

            if(registerRequest.getFirstName()==null  ||
                    registerRequest.getFirstName().isEmpty())
            {
                throw new ValidationException("El nombre del usuario debe ser obligatorio");
            }
            if(registerRequest.getFirstName().length()>200)
            {
                throw new ValidationException("El nombre del usuario no debe exceder los 200 caracteres");
            }
            if(registerRequest.getLastName()==null || registerRequest.getLastName().isEmpty())
            {
                throw new ValidationException("El apellido del usuario debe ser obligatorio");
            }
            if(registerRequest.getLastName().length()>200)
            {
                throw new ValidationException("El apellido del usuario no debe exceder los 200 caracteres");
            }
        }
        else{
            if(registerRequest.getRuc() == null || registerRequest.getRuc().isEmpty()){
                throw new ValidationException("El ruc es obligatorio en una empresa");
            }
            if(registerRequest.getRuc().length() > 30)
            {
                throw new ValidationException("El ruc es demasiado largo");
            }
        }
    }

    @Override
    public void existsUserByEmail(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ValidationException("Ya existe un usuario con el email " + registerRequest.getEmail());
        }
    }
}
