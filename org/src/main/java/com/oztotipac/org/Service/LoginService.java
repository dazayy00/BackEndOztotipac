package com.oztotipac.org.Service;

import com.oztotipac.org.Entity.Admin;
import com.oztotipac.org.Entity.Auth.AuthResponse;
import com.oztotipac.org.Entity.Customer;
import com.oztotipac.org.Entity.Supervisor;
import com.oztotipac.org.Entity.UserType;
import com.oztotipac.org.Repository.AdminRepository;
import com.oztotipac.org.Repository.SupervisorLoginRepository;
import com.oztotipac.org.Repository.CustomerLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private SupervisorLoginRepository supervisorRepository;

    @Autowired
    private CustomerLoginRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Supervisor supervisor = supervisorRepository.findByEmail(email);

        if (supervisor != null) {
            String role = "ROLE_SUPERVISOR";
            return org.springframework.security.core.userdetails.User.builder()
                    .username(supervisor.getEmail())
                    .password(supervisor.getPassword())
                    .authorities(role)
                    .build();
        }

        Customer customer = customerRepository.findByEmail(email);

        if (customer != null) {
            String role = "ROLE_CUSTOMER";
            return org.springframework.security.core.userdetails.User.builder()
                    .username(customer.getEmail())
                    .password(customer.getPassword())
                    .authorities(role)
                    .build();
        }

        Admin admin = adminRepository.findByEmail(email);

        if (admin != null) {
            String role = "ROLE_ADMIN";
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getEmail())
                    .password(admin.getPassword())
                    .authorities(role)
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }

    public AuthResponse login(String email, String password) throws UsernameNotFoundException {
        Supervisor supervisor = supervisorRepository.findByEmail(email);

        if (supervisor != null && passwordEncoder.matches(password, supervisor.getPassword())) {
            return AuthResponse.builder()
                    .idUser(supervisor.getIdUser().toString())
                    .userType(UserType.valueOf("SUPERVISOR"))
                    .email(supervisor.getEmail())
                    .build();
        }

        Customer customer = customerRepository.findByEmail(email);

        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return AuthResponse.builder()
                    .idUser(customer.getIdUser().toString())
                    .userType(UserType.valueOf("CUSTOMER"))
                    .email(customer.getEmail())
                    .build();
        }

        Admin admin = adminRepository.findByEmail(email);

        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return AuthResponse.builder()
                    .idUser(admin.getIdUser().toString())
                    .userType(UserType.valueOf("ADMIN"))
                    .email(admin.getEmail())
                    .build();
        }

        throw new UsernameNotFoundException("Invalid credentials");
    }
}
