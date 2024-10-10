package com.oztotipac.org.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Estrategia de herencia
@DiscriminatorColumn(name = "user_type")  // Columna para discriminar el tipo de usuario
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name_paternal", length = 50, nullable = false)
    private String lastNamePaternal;

    @Column(name = "last_name_maternal", length = 50, nullable = false)
    private String lastNameMaternal;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "rfc", length = 13, nullable = false)
    private String rfc;

    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    //@Enumerated(EnumType.STRING)
    //@Column(name = "user_type", nullable = false)
    //private UserType userType;

    // Constructor con userType
    public User(String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, String password) {
        this.firstName = firstName;
        this.lastNamePaternal = lastNamePaternal;
        this.lastNameMaternal = lastNameMaternal;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.rfc = rfc;
        this.email = email;
        this.password = password;
        //this.userType = userType;
    }
}