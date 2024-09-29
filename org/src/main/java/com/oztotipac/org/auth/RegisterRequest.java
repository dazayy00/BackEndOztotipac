package com.oztotipac.org.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "primer nombre o nombres")
    private String first_name;

    @NotBlank(message = "apellidos del usuario")
    private String last_name_paternal;
    private String last_name_maternal;

    @NotBlank(message = "edad de usuario en base a nacimiento")
    private String birthdate;

    @NotBlank(message = "numero de telefono")
    private String phone_number;

    @Email(message = "Email valido")
    @NotBlank(message = "Email de usuario")
    private String email;

    @NotBlank(message = "ingreso de contraseña")
    @Size(min = 8, message = "la contraseña debe de tener como minimo 8 caracteres")
    private String password;
}