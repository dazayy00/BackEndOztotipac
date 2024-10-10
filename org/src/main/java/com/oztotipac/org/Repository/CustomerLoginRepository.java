package com.oztotipac.org.Repository;

import com.oztotipac.org.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerLoginRepository extends CrudRepository<Customer, Long> {
    Customer findByEmail(String email);
}
