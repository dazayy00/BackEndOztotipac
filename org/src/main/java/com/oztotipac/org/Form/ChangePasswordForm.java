package com.oztotipac.org.Form;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePasswordForm implements Serializable {

    @Size(min = 6, message = "{password.min.length}")
    private String oldPassword;

    @Size(min = 6, message = "{password.min.length}")
    private String newPassword;

    @Size(min = 6, message = "{password.min.length}")
    private String confirmPassword;
}
