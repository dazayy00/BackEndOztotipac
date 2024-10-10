package com.oztotipac.org.DTO;

import com.oztotipac.org.Entity.Supervisor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SupervisorDTO {
    private Long idUser;             // ID del usuario (Admin)
    private String firstName;        // Nombre del admin
    private String lastNamePaternal; // Apellido paterno
    private String lastNameMaternal; // Apellido materno
    private LocalDate birthdate;     // Fecha de nacimiento
    private String phoneNumber;      // Número de teléfono
    private String rfc;              // RFC
    private String email;            // Email

    public static SupervisorDTO build(final Supervisor supervisor) {
        return SupervisorDTO.builder()
                .idUser(supervisor.getIdUser())
                .firstName(supervisor.getFirstName())
                .lastNamePaternal(supervisor.getLastNamePaternal())
                .lastNameMaternal(supervisor.getLastNameMaternal())
                .birthdate(supervisor.getBirthdate())
                .phoneNumber(supervisor.getPhoneNumber())
                .rfc(supervisor.getRfc())
                .email(supervisor.getEmail())
                .build();
    }
}
