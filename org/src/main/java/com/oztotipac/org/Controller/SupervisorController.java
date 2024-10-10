package com.oztotipac.org.Controller;

import com.oztotipac.org.DTO.SupervisorDTO;
import com.oztotipac.org.Entity.Supervisor;
import com.oztotipac.org.Form.SupervisorForm;
import com.oztotipac.org.Service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oztotipac/supervisor")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<SupervisorDTO>> getAllSupervisors() {
        List<SupervisorDTO> supervisors = supervisorService.getAllSupervisors();
        return ResponseEntity.ok().body(supervisors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDTO> getSupervisorById(@PathVariable Long id) {
        SupervisorDTO supervisor = supervisorService.getSupervisorById(id);
        return ResponseEntity.ok().body(supervisor);
    }

    @PostMapping("/post")
    public ResponseEntity<SupervisorDTO> createSupervisor(@RequestBody SupervisorForm supervisorForm) {
        Supervisor supervisor = new Supervisor();
        supervisor.setFirstName(supervisorForm.getFirstName());
        supervisor.setLastNamePaternal(supervisorForm.getLastNamePaternal());
        supervisor.setLastNameMaternal(supervisorForm.getLastNameMaternal());
        supervisor.setEmail(supervisorForm.getEmail());

        SupervisorDTO createdSupervisor = supervisorService.createSupervisor(supervisor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupervisor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDTO> updateSupervisor(@PathVariable Long id, @RequestBody SupervisorForm supervisorForm) {
        // Convertir SupervisorForm a Supervisor
        Supervisor updatedSupervisor = new Supervisor();
        updatedSupervisor.setFirstName(supervisorForm.getFirstName());
        updatedSupervisor.setLastNamePaternal(supervisorForm.getLastNamePaternal());
        updatedSupervisor.setLastNameMaternal(supervisorForm.getLastNameMaternal());
        updatedSupervisor.setEmail(supervisorForm.getEmail());
        // Añade otros campos que necesites del formulario

        SupervisorDTO updatedSupervisorDTO = supervisorService.updateSupervisor(id, updatedSupervisor);
        return ResponseEntity.ok(updatedSupervisorDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupervisor(@PathVariable Long id) {
        supervisorService.deleteSupervisor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByPage")
    public ResponseEntity<List<SupervisorDTO>> getSupervisorsByPage(@RequestParam int page, @RequestParam int size) {
        List<SupervisorDTO> supervisors = supervisorService.getSupervisorsByPage(page, size);
        return ResponseEntity.ok(supervisors);
    }

}
