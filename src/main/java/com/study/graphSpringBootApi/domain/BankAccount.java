package com.study.graphSpringBootApi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BankAccount {
    private String id;
    private String clientId;
    private Currency currency;
    private Float balance;
    private String status;
}
