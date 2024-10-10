package com.oztotipac.org.Controller;

import com.oztotipac.org.DTO.AdminDTO;
import com.oztotipac.org.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oztotipac/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok().body(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long idUser) {
        AdminDTO admin = adminService.getAdminById(idUser);
        return ResponseEntity.ok().body(admin);
    }
}
