package com.oztotipac.org.Repository;

import com.oztotipac.org.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
    List<Customer> findByEmailContainingIgnoreCase(String email);
}
