package com.oztotipac.org.DTO;

import com.oztotipac.org.Entity.Admin;
import com.oztotipac.org.Entity.UserType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AdminDTO {
    private Long idUser;
    private String firstName;
    private String lastNamePaternal;
    private String lastNameMaternal;
    private LocalDate birthdate;
    private String phoneNumber;
    private String rfc;
    private String email;
    private LocalDateTime createdAt;
    private UserType userType;

    public static AdminDTO fromAdmin(final Admin admin) {
        return AdminDTO.builder()
                .idUser(admin.getIdUser())
                .firstName(admin.getFirstName())
                .lastNamePaternal(admin.getLastNamePaternal())
                .lastNameMaternal(admin.getLastNameMaternal())
                .birthdate(admin.getBirthdate())
                .phoneNumber(admin.getPhoneNumber())
                .rfc(admin.getRfc())
                .email(admin.getEmail())
                .createdAt(admin.getCreatedAt())
                .userType(admin.getUserType())
                .build();
    }
}
