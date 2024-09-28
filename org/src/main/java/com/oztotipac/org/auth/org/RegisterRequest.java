package com.oztotipac.org.auth.org;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String first_name;
    String last_name_paternal;
    String last_name_maternal;
    String birthdate;
    String phone_number;
    String email;
    String password;
}