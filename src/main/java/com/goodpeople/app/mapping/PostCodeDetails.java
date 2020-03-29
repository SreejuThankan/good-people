package com.goodpeople.app.mapping;

import lombok.Value;

@Value
public class PostCodeDetails {
    private final String postcode;
    private final String country;
    private final String nhs_ha;
    private final String longitude;
    private final String latitude;
    private final String region;
    private final String incode;
    private final String outcode;
    private final String admin_district;
    private final String admin_county;
    private final String admin_ward;
}
