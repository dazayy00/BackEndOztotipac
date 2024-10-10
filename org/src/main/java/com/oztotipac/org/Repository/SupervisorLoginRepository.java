package com.oztotipac.org.Repository;

import com.oztotipac.org.Entity.Supervisor;
import org.springframework.data.repository.CrudRepository;

public interface SupervisorLoginRepository extends CrudRepository<Supervisor, Long> {
    Supervisor findByEmail(String email);
}
