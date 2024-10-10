package com.oztotipac.org.Entity;

import com.oztotipac.org.Form.CustomerForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CUSTOMER")  // Definir el valor de discriminador para Customer
//@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_customer"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {

    //eliminacion de customerhistory
    //@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<CustomerHistory> customerHistory;  // Ajuste de mapeo

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Constructor con inicialización de createdAt
    public Customer(String firstName, String lastNamePaternal, String lastNameMaternal, LocalDate birthdate, String phoneNumber, String rfc, String email, String password) {
        super(firstName, lastNamePaternal, lastNameMaternal, birthdate, phoneNumber, rfc, email, password);
        this.createdAt = LocalDateTime.now(); // Inicializar createdAt
    }

    // Constructor a partir de un CustomerForm
    public Customer(final CustomerForm form) {
        super(form.getFirstName(), form.getLastNamePaternal(), form.getLastNameMaternal(), form.getBirthdate(), form.getPhoneNumber(), form.getRfc(), form.getEmail(), form.getPassword());
        this.createdAt = LocalDateTime.now(); // Inicializar createdAt
    }

    public void updateCustomer(final CustomerForm form) {
        this.setFirstName(form.getFirstName());
        this.setLastNamePaternal(form.getLastNamePaternal());
        this.setLastNameMaternal(form.getLastNameMaternal());
        this.setBirthdate(form.getBirthdate());
        this.setPhoneNumber(form.getPhoneNumber());
        this.setRfc(form.getRfc());
        this.setEmail(form.getEmail());
        this.setPassword(form.getPassword());
        this.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idUser=" + getIdUser() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastNamePaternal='" + getLastNamePaternal() + '\'' +
                ", lastNameMaternal='" + getLastNameMaternal() + '\'' +
                ", birthdate=" + getBirthdate() +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", rfc='" + getRfc() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}

