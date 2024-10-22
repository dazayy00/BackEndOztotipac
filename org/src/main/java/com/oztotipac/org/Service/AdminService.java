package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.AdminDTO;
import com.oztotipac.org.Entity.Admin;
import com.oztotipac.org.Entity.User;
import com.oztotipac.org.Entity.UserType;
import com.oztotipac.org.Exception.ResourceNotFoundException;
import com.oztotipac.org.Form.AdminForm;
import com.oztotipac.org.Repository.AdminRepository;
import com.oztotipac.org.Repository.UserRepository;
import com.oztotipac.org.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminDTO registerAdmin(AdminForm adminForm) {
        Admin admin = convertFormToAdmin(adminForm);
        String encodedPassword = passwordEncoder.encode(adminForm.getPassword());
        admin.setPassword(encodedPassword);
        admin.setCreatedAt(LocalDateTime.now());

        UserType adminType = userTypeRepository.findByTypeName("ADMIN");
        admin.setUserType(adminType);

        Admin savedAdmin = adminRepository.save(admin);
        return AdminDTO.fromAdmin(savedAdmin);
    }

    public List<AdminDTO> getAllAdmins() {
        UserType adminType = userTypeRepository.findByTypeName("ADMIN");

        List<User> admins = userRepository.findByUserType(adminType); // Filtro por ADMIN
        return admins.stream()
                .map(user -> (Admin) user)
                .map(AdminDTO::fromAdmin)
                .collect(Collectors.toList());
    }

    public AdminDTO getAdminById(Long id) {
        return adminRepository.findById(id)
                .map(AdminDTO::fromAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
    }

    private Admin convertFormToAdmin(AdminForm form) {
        Admin admin = new Admin();
        admin.setFirstName(form.getFirstName());
        admin.setLastNamePaternal(form.getLastNamePaternal());
        admin.setLastNameMaternal(form.getLastNameMaternal());
        admin.setEmail(form.getEmail());
        admin.setRfc(form.getRfc());
        admin.setBirthdate(form.getBirthdate());
        admin.setPhoneNumber(form.getPhoneNumber());
        return admin;
    }

}
