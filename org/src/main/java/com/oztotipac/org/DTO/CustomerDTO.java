package com.oztotipac.org.DTO;

import com.oztotipac.org.Entity.Customer;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends UserDTO {
    private Long idUser; // Asegúrate de que este valor esté siendo seteado
    private String firstName;
    private String lastNamePaternal;
    private String lastNameMaternal;
    private LocalDate birthdate;
    private String phoneNumber;
    private String rfc;
    private String email;
    private LocalDateTime createdAt;

    public CustomerDTO(Long idUser, String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, LocalDateTime createdAt) {
        super(idUser, firstName, email, createdAt);
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastNamePaternal = lastNamePaternal;
        this.lastNameMaternal = lastNameMaternal;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.rfc = rfc;
        this.email = email;
        this.createdAt = createdAt;
    }

    public static CustomerDTO build(final Customer customer) {
        return new CustomerDTO(
                customer.getIdUser(),
                customer.getFirstName(),
                customer.getLastNamePaternal(),
                customer.getLastNameMaternal(),
                customer.getBirthdate(),
                customer.getPhoneNumber(),
                customer.getRfc(),
                customer.getEmail(),
                customer.getCreatedAt()
        );
    }
}

