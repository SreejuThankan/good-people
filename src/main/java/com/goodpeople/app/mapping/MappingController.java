package com.goodpeople.app.mapping;

import com.goodpeople.app.medicine.Medicine;
import com.goodpeople.app.medicine.MedicineRepository;
import com.goodpeople.app.person.Person;
import com.goodpeople.app.person.PersonFactoryService;
import com.goodpeople.app.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.goodpeople.app.mapping.MappingController.API_V1;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MappingController {

    public static final String API_V1 = "/api/v1/mapping";

    private final PersonRepository personRepository;
    private final MappingService mappingService;
    private final PersonFactoryService personFactoryService;
    private final MedicineRepository medicineRepository;

    @GetMapping("closest-people")
    public List<Person> getClosetPeopleWithMedicine(@PathVariable("personId") String id,
                                                    @RequestParam("medicine") String medicineId) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unknown id"));
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new IllegalArgumentException("Unknown medicine id"));
        return mappingService.get10QuickestDeliveryPeople(person, medicine);
    }
}
