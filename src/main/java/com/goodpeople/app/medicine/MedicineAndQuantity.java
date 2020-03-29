package com.goodpeople.app.medicine;

import lombok.Value;

@Value
public class MedicineAndQuantity {
    private final Medicine medicine;
    private final int quantity;
}
