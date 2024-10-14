package com.oztotipac.org.Controller;

import com.oztotipac.org.DTO.CustomerDTO;
import com.oztotipac.org.Form.CustomerForm;
import com.oztotipac.org.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oztotipac/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers= customerService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/create") // Endpoint original para crear clientes
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerForm customerForm) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PostMapping("/register") // Nuevo endpoint para registrar un cliente
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody CustomerForm customerForm) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerForm customerForm) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerForm);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        List<CustomerDTO> customers = customerService.searchCustomers(name, email);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/getByPage")
    public ResponseEntity<List<CustomerDTO>> getCustomersByPage(@RequestParam int page, @RequestParam int size) {
        List<CustomerDTO> customers = customerService.getCustomersByPage(page, size);
        return ResponseEntity.ok(customers);
    }

}
