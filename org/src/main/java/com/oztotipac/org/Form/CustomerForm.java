package com.oztotipac.org.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CustomerForm implements Serializable {

    @Size(max = 100, message = "{name.right.length}")
    private String firstName;

    @Size(max = 100, message = "{lastName.right.length}")
    private String lastNamePaternal;

    private String lastNameMaternal;

    @Past(message = "{birthdate.must.be.past}")
    private LocalDate birthdate;

    @Size(min = 13, max = 13, message = "{rfc.right.length}")
    private String rfc;

    @Size(max = 15, message = "{phoneNumber.right.length}")
    private String phoneNumber;

    @Email(message = "{wrong.email.structure}")
    private String email;

    @Size(min = 6, message = "{password.min.length}")
    private String password;
}