package com.oztotipac.org.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long idUser;
    private String firstName;
    private String lastNamePaternal;
    private String lastNameMaternal;
    private LocalDate birthdate;
    private String phoneNumber;
    private String rfc;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    public UserDTO(Long idUser, String firstName, String email, LocalDateTime createdAt) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.email = email;
        this.createdAt = createdAt;
    }
}
