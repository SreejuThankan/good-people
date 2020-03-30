package com.goodpeople.app.mapping;

import com.goodpeople.app.medicine.Medicine;
import com.goodpeople.app.person.Person;

import java.util.List;

public interface MappingService {


    List<Person> get10QuickestDeliveryPeople(Person person, Medicine requiredMedicine);

    RouteSummary getRouteSummaryBetween(String startPostCodeStr, String destPostCodeStr, RouteType routeType);
}
