package com.oztotipac.org.auth;

import com.oztotipac.org.jwt.JwtService;
import com.oztotipac.org.user.Role;
import com.oztotipac.org.user.Users;
import com.oztotipac.org.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthReponse login(LoginRequest request) {

        //autentica usuario
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Credenciales invalidas");
        }


        //obtiene detalles de usuario
        UserDetails user=userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        //genera token jwt
        String token= jwtService.getToken(user);
        return AuthReponse.builder()
                .token(token)
                .build();
    }

    public AuthReponse register(RegisterRequest request) {

        //verifica existencia de usuario
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Correo ya existente");
        }

        //crea nuevo usuario
        Users user = Users.builder()
                .firstName(request.getFirst_name())
                .lastNamePaternal(request.getLast_name_paternal())
                .lastNameMaternal(request.getLast_name_maternal())
                .birthdate(request.getBirthdate())
                .email(request.getEmail())
                .phoneNumber(request.getPhone_number())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();


        userRepository.save(user);

        return AuthReponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}