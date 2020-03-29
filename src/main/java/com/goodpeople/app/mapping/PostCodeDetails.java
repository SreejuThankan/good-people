package com.goodpeople.app.mapping;

import lombok.Value;

@Value
public class PostCodeDetails {
    private final String postcode;
    private final String country;
    private final String nhsHa;
    private final String longitude;
    private final String latitude;
    private final String region;
    private final String incode;
    private final String outcode;
    private final String adminDistrict;
    private final String adminCounty;
    private final String adminWard;

    public String getCity() {
        return adminDistrict;
    }
}
