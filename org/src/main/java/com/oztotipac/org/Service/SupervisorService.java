package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.SupervisorDTO;
import com.oztotipac.org.Entity.Supervisor;
import com.oztotipac.org.Repository.SupervisorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    public SupervisorDTO getSupervisorById(Long id) {
        Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor not found"));
        return SupervisorDTO.build(supervisor); // Conversión a DTO
    }

    public SupervisorDTO createSupervisor(Supervisor supervisor) {
        supervisor.setCreatedAt(LocalDateTime.now());
        Supervisor savedSupervisor = supervisorRepository.save(supervisor);

        return SupervisorDTO.build(savedSupervisor);
    }


    public SupervisorDTO updateSupervisor(Long id, Supervisor updatedSupervisor) {
        Supervisor existingSupervisor = findSupervisorById(id);

        // Actualizar los campos necesarios
        existingSupervisor.setUpdatedAt(LocalDateTime.now());
        existingSupervisor.setFirstName(updatedSupervisor.getFirstName());
        existingSupervisor.setLastNamePaternal(updatedSupervisor.getLastNamePaternal());
        existingSupervisor.setLastNameMaternal(updatedSupervisor.getLastNameMaternal());
        existingSupervisor.setEmail(updatedSupervisor.getEmail());

        // Guardar y devolver el supervisor actualizado como DTO
        Supervisor savedSupervisor = supervisorRepository.save(existingSupervisor);
        return SupervisorDTO.build(savedSupervisor); // Conversión a DTO
    }


    public void deleteSupervisor(Long id) {
        Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor not found"));
        supervisor.setDeletedAt(LocalDateTime.now());
        supervisorRepository.save(supervisor);  // Eliminación lógica
    }

    public List<SupervisorDTO> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorRepository.findAll();
        return supervisors.stream()
                .map(SupervisorDTO::build)
                .collect(Collectors.toList());  // Conversión a DTO
    }

    public List<SupervisorDTO> getSupervisorsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Supervisor> supervisorPage = supervisorRepository.findAll(pageable);
        return supervisorPage.getContent().stream()
                .map(SupervisorDTO::build)
                .collect(Collectors.toList());  // Conversión a DTO
    }

    public Supervisor findSupervisorById(Long id) {
        return supervisorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor not found"));
    }

}
