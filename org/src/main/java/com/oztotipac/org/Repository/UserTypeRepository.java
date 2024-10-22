package com.oztotipac.org.Repository;

import com.oztotipac.org.Entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByTypeName(String typeName);
}
