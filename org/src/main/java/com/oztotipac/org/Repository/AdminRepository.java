package com.oztotipac.org.Repository;

import com.oztotipac.org.Entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByEmail(String email);
}
