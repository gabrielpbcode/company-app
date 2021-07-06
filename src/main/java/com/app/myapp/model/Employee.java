package com.app.myapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(columnDefinition = "real default 0")
    private BigDecimal wage;

    @Column(name = "company_id", insertable = false, updatable = false)
    private Long companyId;

    @ManyToOne
    @JsonBackReference
    private Company company;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
    @Version
    private Long version;

    public Employee(String name, String cpf, Date birthday, BigDecimal wage) {
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
        this.wage = wage;
    }

    @PrePersist
    public void prePersist() {
        Calendar c = Calendar.getInstance();
        createdAt = c.getTime();
        updatedAt = c.getTime();
    }

    @PreUpdate
    public void preUpdate() {
        Calendar c = Calendar.getInstance();
        updatedAt = c.getTime();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            Employee emp = (Employee) obj;
            return this.cpf.equals(emp.getCpf());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
