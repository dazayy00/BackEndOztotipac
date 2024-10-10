package com.oztotipac.org.Repository;

import com.oztotipac.org.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserRepository extends JpaRepository<User, Long> {
}
