package com.oztotipac.org.DTO;

import com.oztotipac.org.Entity.Admin;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AdminDTO {
    private Long idUser;             // ID del usuario (Admin)
    private String firstName;        // Nombre del admin
    private String lastNamePaternal; // Apellido paterno
    private String lastNameMaternal; // Apellido materno
    private LocalDate birthdate;     // Fecha de nacimiento
    private String phoneNumber;      // Número de teléfono
    private String rfc;              // RFC
    private String email;            // Email

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
                .build();
    }
}
