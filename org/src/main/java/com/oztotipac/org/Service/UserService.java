package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.UserDTO;
import com.oztotipac.org.Entity.User;
import com.oztotipac.org.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastNamePaternal(userDTO.getLastNamePaternal());
        user.setLastNameMaternal(userDTO.getLastNameMaternal());
        user.setBirthdate(userDTO.getBirthdate());
        user.setRfc(userDTO.getRfc());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());

        // Cifrar la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
