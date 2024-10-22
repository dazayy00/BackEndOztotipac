package com.oztotipac.org.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_type")
    private Long idUserType;

    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;
}
