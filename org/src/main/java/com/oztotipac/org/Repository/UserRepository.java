package com.oztotipac.org.Repository;

import com.oztotipac.org.Entity.User;
import com.oztotipac.org.Entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByUserType(UserType userType);
}
