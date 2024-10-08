package com.oztotipac.org.Controller;

import com.oztotipac.org.DTO.UserDTO;
import com.oztotipac.org.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.ok("Usuario registrado con exito");
    }
}
