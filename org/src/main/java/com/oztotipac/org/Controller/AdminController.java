package com.oztotipac.org.Controller;

import com.oztotipac.org.DTO.AdminDTO;
import com.oztotipac.org.Form.AdminForm;
import com.oztotipac.org.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oztotipac/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<AdminDTO> registerAdmin(@Valid @RequestBody AdminForm adminForm) {
        AdminDTO registeredAdmin = adminService.registerAdmin(adminForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredAdmin);
    }

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
