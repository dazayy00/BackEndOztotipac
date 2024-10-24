package com.oztotipac.org.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginForm implements Serializable {

    @Email(message = "{wrong.email.structure}")
    private String email;

    @Size(min = 8, message = "{password.min.length}")
    private String password;
}
