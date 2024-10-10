package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.AdminDTO;
import com.oztotipac.org.Exception.ResourceNotFoundException;
import com.oztotipac.org.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<AdminDTO> getAllAdmins() {
        return StreamSupport.stream(adminRepository.findAll().spliterator(), false)
                .map(AdminDTO::fromAdmin)
                .collect(Collectors.toList());
    }

    public AdminDTO getAdminById(Long id) {
        return adminRepository.findById(id)
                .map(AdminDTO::fromAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
    }
}
