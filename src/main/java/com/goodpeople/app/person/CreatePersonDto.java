package com.goodpeople.app.person;

import lombok.Value;

import java.util.List;

@Value
public class CreatePersonDto {
    private final String firstName;
    private final String lastName;
    private final String addressLine1;
    private final String addressLine2;
    private final String postCode;
    private final String phoneNo;
    private final String email;
    private final List<String> routeTypes;
}
