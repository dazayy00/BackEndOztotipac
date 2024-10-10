package com.oztotipac.org.Controller;

import com.oztotipac.org.Entity.Auth.AuthResponse;
import com.oztotipac.org.Entity.Auth.LoginRequest;
import com.oztotipac.org.Service.LoginService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oztotipac/auth")
public class LoginController {
    private final AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager =authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = loginService.login(request.getEmail(), request.getPassword());

        if (response != null) {
            return ResponseEntity.ok().body(
                    response
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
