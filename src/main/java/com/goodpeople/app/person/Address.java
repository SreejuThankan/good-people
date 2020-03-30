package com.goodpeople.app.person;

import com.goodpeople.app.mapping.PostCodeDetails;
import lombok.Value;

@Value
public class Address {
    private final String addressLine1;
    private final String addressLine2;
    private final PostCodeDetails postCodeDetails;

}
