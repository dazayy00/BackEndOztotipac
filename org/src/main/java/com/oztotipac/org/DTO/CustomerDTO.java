package com.oztotipac.org.DTO;

import com.oztotipac.org.Entity.Customer;
import com.oztotipac.org.Entity.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends UserDTO {

    private LocalDateTime UpdatedAt;
    private LocalDateTime DeletedAt;

    public CustomerDTO(Long idUser, String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, String password, LocalDateTime createdAt, UserType userType, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        super(idUser, firstName, lastNamePaternal, lastNameMaternal, birthdate, phoneNumber, rfc, email, password, createdAt, userType);
        this.UpdatedAt = updatedAt;
        this.DeletedAt = deletedAt;
    }

    public static CustomerDTO build(final Customer customer) {
        return CustomerDTO.builder()
                .idUser(customer.getIdUser())
                .firstName(customer.getFirstName())
                .lastNamePaternal(customer.getLastNamePaternal())
                .lastNameMaternal(customer.getLastNameMaternal())
                .birthdate(customer.getBirthdate())
                .phoneNumber(customer.getPhoneNumber())
                .rfc(customer.getRfc())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .createdAt(customer.getCreatedAt())
                .userType(customer.getUserType())
                .UpdatedAt(customer.getUpdatedAt())
                .DeletedAt(customer.getDeletedAt())
                .build();
    }
}

