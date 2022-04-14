package com.bsf.resource.persistence.model;

import com.bsf.resource.persistence.model.audit.DateAudit;
import com.bsf.resource.persistence.model.contract.EmployeeInterface;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee extends DateAudit implements EmployeeInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Level> levels;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name = "telephone", columnDefinition = "varchar(25)", nullable = true)
    private String telephone;

    public Employee() {}

    public Employee(long id, String firstName, String lastName, String email, String telephone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", levels=" + levels +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }


}
