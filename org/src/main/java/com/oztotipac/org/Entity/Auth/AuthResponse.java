package com.oztotipac.org.Entity.Auth;

import com.oztotipac.org.Entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    String idUser;
    UserType userType;
    String email;
}
