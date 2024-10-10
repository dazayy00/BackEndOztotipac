package com.oztotipac.org.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {

    // Constructor que asigna userType como Admin
    public Admin(String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, String password) {
        super(firstName, lastNamePaternal, lastNameMaternal, birthdate, phoneNumber, rfc, email, password);
    }
}

