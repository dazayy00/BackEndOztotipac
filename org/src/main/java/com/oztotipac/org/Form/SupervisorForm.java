package com.oztotipac.org.Form;

import com.oztotipac.org.Entity.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SupervisorForm implements Serializable {

    @Email(message = "{wrong.email.structure}")
    private String email;

    @Past(message = "{birthdate.must.be.past}")
    private LocalDate birthdate;

    @Size(max = 13, message = "{phoneNumber.right.length}")
    private String phoneNumber;

    @Size(max = 100, message = "{name.right.length}")
    private String firstName;

    @Size(min = 12, max = 13, message = "{rfc.right.length}")
    private String rfc;

    @Size(max = 100, message = "{lastName.right.length}")
    private String lastNamePaternal;

    private String lastNameMaternal;

    @Size(min = 8, message = "{password.min.length}")
    private String password;

    private UserType userType;
}
