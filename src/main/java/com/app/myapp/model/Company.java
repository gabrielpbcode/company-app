package com.app.myapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String fantasyName;

    @Column(columnDefinition = "real default 0.0")
    private BigDecimal funds;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
    @Version
    private Long version;

    public Company(String name, String fantasyName, BigDecimal funds) {
        this.name = name;
        this.fantasyName = fantasyName;
        this.funds = funds;
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
        if (obj instanceof Company) {
            Company comp = (Company) obj;
            return this.id.equals(comp.getId()) && this.name.equals(comp.getName());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
}
