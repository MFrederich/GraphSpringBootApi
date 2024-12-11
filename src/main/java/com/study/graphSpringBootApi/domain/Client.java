package com.study.graphSpringBootApi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String countryCode;
}
