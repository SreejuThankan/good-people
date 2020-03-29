package com.goodpeople.app.person;

import com.goodpeople.app.mapping.MappingService;
import com.goodpeople.app.medicine.Medicine;
import com.goodpeople.app.medicine.MedicineAndQuantity;
import com.goodpeople.app.medicine.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.goodpeople.app.person.PersonController.API_V1;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    public static final String API_V1 = "/api/v1/person";

    private final PersonRepository personRepository;
    private final MappingService mappingService;
    private final PersonFactoryService personFactoryService;
    private final MedicineRepository medicineRepository;

    @GetMapping("/all")
    public List<Person> allPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> personById(@PathVariable("id") String id) {
        return personRepository.findById(id);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody CreatePersonDto createPersonDto) {
        return personFactoryService.createAndSave(createPersonDto);
    }

    @PostMapping(value = "/{id}/owned", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person addOwnedMedicine(@PathVariable("id") String id, @RequestBody MedicineAndQuantity owned) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unknown id"));
        if (!medicineRepository.findById(owned.getMedicine().getId()).isPresent()) {
            throw new IllegalArgumentException("Medicine does not exist.");
        }
        person.addOwnedMedicine(owned);
        return personRepository.save(person);
    }

    @GetMapping("/{id}")
    public List<Person> getClosetPeopleWithMedicine(@PathVariable("id") String id,
                                                    @RequestParam("medicine") String medicineId) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unknown id"));
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new IllegalArgumentException("Unknown medicine id"));
        return mappingService.get10QuickestDeliveryPeople(person, medicine);
    }

}
