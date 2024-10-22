package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.UserDTO;
import com.oztotipac.org.Entity.User;
import com.oztotipac.org.Entity.UserType;
import com.oztotipac.org.Exception.ResourceNotFoundException;
import com.oztotipac.org.Form.UserForm;
import com.oztotipac.org.Repository.UserRepository;
import com.oztotipac.org.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO register(UserForm userForm) {
        User user = convertFormToUser(userForm);
        String encodedPassword = passwordEncoder.encode(userForm.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedAt(LocalDateTime.now());

        UserType userType = userTypeRepository.findByTypeName("USER");
        user.setUserType(userType);

        User savedUser = userRepository.save(user);
        return UserDTO.builder()
                .idUser(savedUser.getIdUser())
                .firstName(savedUser.getFirstName())
                .lastNamePaternal(savedUser.getLastNamePaternal())
                .lastNameMaternal(savedUser.getLastNameMaternal())
                .birthdate(savedUser.getBirthdate())
                .phoneNumber(savedUser.getPhoneNumber())
                .rfc(savedUser.getRfc())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .createdAt(savedUser.getCreatedAt())
                .userType(savedUser.getUserType())
                .build();
    }

    public UserDTO getUserById(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserDTO.builder()
                .idUser(user.getIdUser())
                .firstName(user.getFirstName())
                .lastNamePaternal(user.getLastNamePaternal())
                .lastNameMaternal(user.getLastNameMaternal())
                .birthdate(user.getBirthdate())
                .phoneNumber(user.getPhoneNumber())
                .rfc(user.getRfc())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .userType(user.getUserType())
                .build();
    }

    public void deleteUser(Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);  // Eliminación lógica
    }

    public List<UserDTO> getAllUsers() {
        UserType userType = userTypeRepository.findByTypeName("USER");

        List<User> users = userRepository.findByUserType(userType);
        return users.stream()
                .map(user -> UserDTO.builder()
                        .idUser(user.getIdUser())
                        .firstName(user.getFirstName())
                        .lastNamePaternal(user.getLastNamePaternal())
                        .lastNameMaternal(user.getLastNameMaternal())
                        .birthdate(user.getBirthdate())
                        .phoneNumber(user.getPhoneNumber())
                        .rfc(user.getRfc())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .createdAt(user.getCreatedAt())
                        .userType(user.getUserType())
                        .build())
                .collect(Collectors.toList());
    }

    private User convertFormToUser(UserForm form) {
        User user = new User();

        user.setFirstName(form.getFirstName());
        user.setLastNamePaternal(form.getLastNamePaternal());
        user.setLastNameMaternal(form.getLastNameMaternal());
        user.setEmail(form.getEmail());
        user.setRfc(form.getRfc());
        user.setBirthdate(form.getBirthdate());
        user.setPhoneNumber(form.getPhoneNumber());
        return user;
    }
}
