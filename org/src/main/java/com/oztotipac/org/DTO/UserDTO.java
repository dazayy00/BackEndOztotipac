package com.oztotipac.org.DTO;

import com.oztotipac.org.Entity.UserType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
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
    private UserType userType;

    public UserDTO(Long idUser, String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, String password, LocalDateTime createdAt, UserType userType) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastNamePaternal = lastNamePaternal;
        this.lastNameMaternal = lastNameMaternal;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.rfc = rfc;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.userType = userType;
    }
}
