package com.oztotipac.org.auth.org;

import com.oztotipac.org.jwt.org.JwtService;
import com.oztotipac.org.user.org.Role;
import com.oztotipac.org.user.org.Users;
import com.oztotipac.org.user.org.UserRepository;

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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request, request));
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token= jwtService.getToken(user);
        return AuthReponse.builder()
                .token(token)
                .build();
    }

    public AuthReponse register(RegisterRequest request) {

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
