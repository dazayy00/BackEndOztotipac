package com.oztotipac.org.Service;

import com.oztotipac.org.Entity.Admin;
import com.oztotipac.org.Entity.Auth.AuthResponse;
import com.oztotipac.org.Entity.Customer;
import com.oztotipac.org.Entity.Supervisor;
import com.oztotipac.org.Entity.UserType;
import com.oztotipac.org.Repository.AdminRepository;
import com.oztotipac.org.Repository.SupervisorLoginRepository;
import com.oztotipac.org.Repository.CustomerLoginRepository;
import com.oztotipac.org.Repository.UserTypeRepository;
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

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Supervisor supervisor = supervisorRepository.findByEmail(email);

        if (supervisor != null) {
            return buildUserDetails(supervisor.getEmail(), supervisor.getPassword(), "SUPERVISOR");
        }

        Customer customer = customerRepository.findByEmail(email);

        if (customer != null) {
            return buildUserDetails(customer.getEmail(), customer.getPassword(), "CUSTOMER");
        }

        Admin admin = adminRepository.findByEmail(email);

        if (admin != null) {
            return buildUserDetails(admin.getEmail(), admin.getPassword(), "ADMIN");
        }

        throw new UsernameNotFoundException("User not found");
    }

    private UserDetails buildUserDetails(String email, String password, String role) {
        String rolePrefix = "ROLE_";
        return org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(password)
                .authorities(rolePrefix + role)
                .build();
    }

    public AuthResponse login(String email, String password) throws UsernameNotFoundException {
        Supervisor supervisor = supervisorRepository.findByEmail(email);

        if (supervisor != null && passwordEncoder.matches(password, supervisor.getPassword())) {
            return buildAuthResponse(supervisor.getIdUser(), "SUPERVISOR", supervisor.getEmail());
        }

        Customer customer = customerRepository.findByEmail(email);

        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return buildAuthResponse(customer.getIdUser(), "CUSTOMER", customer.getEmail());
        }

        Admin admin = adminRepository.findByEmail(email);

        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return buildAuthResponse(admin.getIdUser(), "ADMIN", admin.getEmail());
        }

        throw new UsernameNotFoundException("Invalid credentials");
    }

    private AuthResponse buildAuthResponse(Long idUser, String roleName, String email) {
        UserType userType = userTypeRepository.findByTypeName(roleName);

        return AuthResponse.builder()
                .idUser(idUser.toString())
                .userType(userType)
                .email(email)
                .build();
    }
}
