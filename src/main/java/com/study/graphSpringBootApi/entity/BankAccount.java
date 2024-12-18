package com.study.graphSpringBootApi.entity;

import com.study.graphSpringBootApi.domain.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "BankAccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Column
    private Long clientId;

    @Column
    private Currency currency;

    @Column
    private String country;

    @Column
    private Float balance;

    @Column
    private String status;

    @Column
    private Float transferLimit;

    @Column
    private LocalDateTime accountCreateDate;
}
