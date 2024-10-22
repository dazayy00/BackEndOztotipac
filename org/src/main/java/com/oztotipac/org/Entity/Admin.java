package com.oztotipac.org.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Admin(String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, String password, UserType userType) {
        super(firstName, lastNamePaternal, lastNameMaternal, birthdate, phoneNumber, rfc, email, password, userType);
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Admin{" +
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

