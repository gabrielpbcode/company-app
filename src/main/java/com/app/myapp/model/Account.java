package com.app.myapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String agencyNumber;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(columnDefinition = "real default 0")
    private BigDecimal balance;

    @OneToOne
    private Company company;

    @OneToOne
    private Employee employee;
}
