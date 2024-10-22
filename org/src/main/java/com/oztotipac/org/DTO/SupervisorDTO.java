package com.oztotipac.org.DTO;

import com.oztotipac.org.Entity.Customer;
import com.oztotipac.org.Entity.Supervisor;
import com.oztotipac.org.Entity.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
public class SupervisorDTO {

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

    public SupervisorDTO(Long idUser, String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, LocalDateTime createdAt, UserType userType) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastNamePaternal = lastNamePaternal;
        this.lastNameMaternal = lastNameMaternal;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.rfc = rfc;
        this.email = email;
        this.createdAt = createdAt;
        this.userType = userType;
    }


    public static SupervisorDTO build(final Supervisor supervisor) {
        return new SupervisorDTO(
                supervisor.getIdUser(),
                supervisor.getFirstName(),
                supervisor.getLastNamePaternal(),
                supervisor.getLastNameMaternal(),
                supervisor.getBirthdate(),
                supervisor.getPhoneNumber(),
                supervisor.getRfc(),
                supervisor.getEmail(),
                supervisor.getCreatedAt(),
                supervisor.getUserType()  // Incluimos el UserType
        );
    }
}
