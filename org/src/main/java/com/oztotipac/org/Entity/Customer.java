package com.oztotipac.org.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "id_user")
public class Customer extends User {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id_customer")
    //private Long idCustomer;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Customer(String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, String password, UserType userType) {
        super(firstName, lastNamePaternal, lastNameMaternal, birthdate, phoneNumber, rfc, email, password, userType);
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idUser=" + getIdUser() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastNamePaternal='" + getLastNamePaternal() + '\'' +
                ", lastNameMaternal='" + getLastNameMaternal() + '\'' +
                ", birthdate=" + getBirthdate() +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", rfc='" + getRfc() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}

