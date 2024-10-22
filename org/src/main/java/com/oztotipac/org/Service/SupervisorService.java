package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.SupervisorDTO;
import com.oztotipac.org.Entity.Supervisor;
import com.oztotipac.org.Entity.User;
import com.oztotipac.org.Entity.UserType;
import com.oztotipac.org.Exception.ResourceNotFoundException;
import com.oztotipac.org.Form.SupervisorForm;
import com.oztotipac.org.Repository.SupervisorRepository;
import com.oztotipac.org.Repository.UserRepository;
import com.oztotipac.org.Repository.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SupervisorDTO getSupervisorById(Long id) {
        Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found"));
        return SupervisorDTO.build(supervisor); // Convertir a DTO
    }

    public SupervisorDTO registerSupervisor(SupervisorForm supervisorForm) {
        Supervisor supervisor = convertFormToSupervisor(supervisorForm);
        String encodedPassword = passwordEncoder.encode(supervisorForm.getPassword());
        supervisor.setPassword(encodedPassword);
        supervisor.setCreatedAt(LocalDateTime.now());

        UserType supervisorType = userTypeRepository.findByTypeName("SUPERVISOR");
        supervisor.setUserType(supervisorType);

        Supervisor savedSupervisor = supervisorRepository.save(supervisor);
        return SupervisorDTO.build(savedSupervisor);  // Convertir a DTO
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
        return SupervisorDTO.build(savedSupervisor); // Convertir a DTO
    }

    public void deleteSupervisor(Long id) {
        Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found"));
        supervisor.setDeletedAt(LocalDateTime.now());
        supervisorRepository.save(supervisor);  // Eliminación lógica
    }

    public List<SupervisorDTO> getAllSupervisors() {
        UserType supervisorType = userTypeRepository.findByTypeName("SUPERVISOR");

        List<User> supervisors = userRepository.findByUserType(supervisorType);
        return supervisors.stream()
                .map(user -> (Supervisor) user)
                .map(SupervisorDTO::build)
                .collect(Collectors.toList());
    }

    public List<SupervisorDTO> getSupervisorsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Supervisor> supervisorPage = supervisorRepository.findAll(pageable);
        return supervisorPage.getContent().stream()
                .map(SupervisorDTO::build)
                .collect(Collectors.toList());
    }

    public Supervisor findSupervisorById(Long id) {
        return supervisorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor not found"));
    }


    private Supervisor convertFormToSupervisor(SupervisorForm form) {
        Supervisor supervisor = new Supervisor();
        supervisor.setFirstName(form.getFirstName());
        supervisor.setLastNamePaternal(form.getLastNamePaternal());
        supervisor.setLastNameMaternal(form.getLastNameMaternal());
        supervisor.setPhoneNumber(form.getPhoneNumber());
        supervisor.setEmail(form.getEmail());
        supervisor.setPassword(form.getPassword()); // La contraseña será encriptada
        return supervisor;
    }

    //private SupervisorDTO convertToDTO(Supervisor supervisor) {
        //return SupervisorDTO.build(supervisor);
    //}
}
