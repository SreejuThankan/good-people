package com.goodpeople.app.person;

import com.goodpeople.app.mapping.RouteType;
import com.goodpeople.app.medicine.MedicineAndQuantity;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Builder(toBuilder = true)
@Data
@Document(collection = "person")
public class Person {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String email;
    private EnumSet<RouteType> routeTypes;
    @Singular
    private List<MedicineAndQuantity> ownedMedicines = new ArrayList<>();
    @Singular
    private List<MedicineAndQuantity> requiredMedicines = new ArrayList<>();

    public void addOwnedMedicine(MedicineAndQuantity owned) {
        if (ownedMedicines == null) ownedMedicines = new ArrayList<>();
        this.ownedMedicines.add(owned);
    }

    public void addRequiredMedicine(MedicineAndQuantity required) {
        if (requiredMedicines == null) requiredMedicines = new ArrayList<>();
        this.requiredMedicines.add(required);
    }

    public String getPostcode() {
        return address.getPostCodeDetails().getPostcode();
    }

    public String getCity() {
        return address.getPostCodeDetails().getCity();
    }
}
