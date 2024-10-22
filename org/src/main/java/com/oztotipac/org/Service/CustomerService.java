package com.oztotipac.org.Service;

import com.oztotipac.org.DTO.CustomerDTO;
import com.oztotipac.org.Entity.Customer;
import com.oztotipac.org.Entity.User;
import com.oztotipac.org.Entity.UserType;
import com.oztotipac.org.Exception.ResourceNotFoundException;
import com.oztotipac.org.Form.CustomerForm;
import com.oztotipac.org.Repository.CustomerRepository;
import com.oztotipac.org.Repository.UserRepository;
import com.oztotipac.org.Repository.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return convertToDTO(customer);  // Convertir a DTO antes de devolver
    }

    public CustomerDTO registerCustomer(CustomerForm customerForm) {
        Customer customer = convertFormToCustomer(customerForm);
        String encodedPassword = passwordEncoder.encode(customerForm.getPassword());
        customer.setPassword(encodedPassword);
        customer.setCreatedAt(LocalDateTime.now());

        UserType customerType = userTypeRepository.findByTypeName("CUSTOMER");
        customer.setUserType(customerType);

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerDTO.build(savedCustomer);  // Convertir a DTO
    }

    public CustomerDTO updateCustomer(Long id, CustomerForm customerForm) {
        Customer existingCustomer = findCustomerById(id);
        updateCustomerDetails(existingCustomer, customerForm);  // Actualizar detalles
        existingCustomer.setUpdatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(existingCustomer);
        return CustomerDTO.build(savedCustomer);  // Convertir a DTO
    }

    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customer.setDeletedAt(LocalDateTime.now());
        customerRepository.save(customer);  // Eliminación lógica
    }

    public List<CustomerDTO> getAllCustomers() {
        UserType customerType = userTypeRepository.findByTypeName("CUSTOMER");

        List<User> customers = userRepository.findByUserType(customerType);
        return customers.stream()
                .map(user -> (Customer) user)
                .map(CustomerDTO::build)
                .collect(Collectors.toList());
    }


    private CustomerDTO convertToDTO(Customer customer) {
        return CustomerDTO.build(customer);
    }

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

    private void updateCustomerDetails(Customer existingCustomer, CustomerForm form) {
        existingCustomer.setFirstName(form.getFirstName());
        existingCustomer.setLastNamePaternal(form.getLastNamePaternal());
        existingCustomer.setLastNameMaternal(form.getLastNameMaternal());
        existingCustomer.setBirthdate(form.getBirthdate());
        existingCustomer.setPhoneNumber(form.getPhoneNumber());
        existingCustomer.setRfc(form.getRfc());
        existingCustomer.setEmail(form.getEmail());
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }
}
