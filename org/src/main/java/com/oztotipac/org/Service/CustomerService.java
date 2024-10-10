package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.CustomerDTO;
import com.oztotipac.org.Entity.Customer;
import com.oztotipac.org.Form.CustomerForm;
import com.oztotipac.org.Repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return convertToDTO(customer);  // Convertir a DTO antes de devolver
    }

    public CustomerDTO createCustomer(CustomerForm customerForm) {
        Customer customer = convertFormToCustomer(customerForm);
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);  // Convertir a DTO
    }

    public CustomerDTO updateCustomer(Long id, CustomerForm customerForm) {
        Customer existingCustomer = findCustomerById(id);
        updateCustomerDetails(existingCustomer, customerForm);  // Actualizar detalles
        existingCustomer.setUpdatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(existingCustomer);
        return convertToDTO(savedCustomer);  // Convertir a DTO
    }

    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customer.setDeletedAt(LocalDateTime.now());
        customerRepository.save(customer);  // Eliminación lógica
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getCustomersByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customersPage = customerRepository.findAll(pageable);
        return customersPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> searchCustomers(String firstName, String email) {
        List<Customer> customers;
        if (firstName != null && !firstName.isEmpty()) {
            customers = customerRepository.findByFirstNameContainingIgnoreCase(firstName);
        } else if (email != null && !email.isEmpty()) {
            customers = customerRepository.findByEmailContainingIgnoreCase(email);
        } else {
            customers = new ArrayList<>();
        }
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CustomerDTO convertToDTO(Customer customer) {
        return CustomerDTO.build(customer);
    }

    // Convertir CustomerForm a entidad Customer
    private Customer convertFormToCustomer(CustomerForm form) {
        Customer customer = new Customer();
        customer.setFirstName(form.getFirstName());
        customer.setLastNamePaternal(form.getLastNamePaternal());
        customer.setLastNameMaternal(form.getLastNameMaternal());
        customer.setBirthdate(form.getBirthdate());
        customer.setPhoneNumber(form.getPhoneNumber());
        customer.setRfc(form.getRfc());
        customer.setEmail(form.getEmail());
        customer.setPassword(form.getPassword());
        return customer;
    }

    // Actualizar detalles de un Customer existente
    private void updateCustomerDetails(Customer existingCustomer, CustomerForm form) {
        existingCustomer.setFirstName(form.getFirstName());
        existingCustomer.setLastNamePaternal(form.getLastNamePaternal());
        existingCustomer.setLastNameMaternal(form.getLastNameMaternal());
        existingCustomer.setBirthdate(form.getBirthdate());
        existingCustomer.setPhoneNumber(form.getPhoneNumber());
        existingCustomer.setRfc(form.getRfc());
        existingCustomer.setEmail(form.getEmail());
        existingCustomer.setPassword(form.getPassword());
    }

    // Encontrar un Customer por ID
    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
}