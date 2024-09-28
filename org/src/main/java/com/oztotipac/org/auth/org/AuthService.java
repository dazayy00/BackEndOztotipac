package com.oztotipac.org.auth.org;

import com.oztotipac.org.user.org.Role;
import com.oztotipac.org.user.org.Users;
import com.oztotipac.org.user.org.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthReponse login(LoginRequest request) {
        return null;
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
                .password(request.getPassword())
                .build();


        userRepository.save(user);

        return AuthReponse.builder()
                .token(null)
                .build();
    }
}
