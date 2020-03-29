package com.goodpeople.app.medicine;

import com.goodpeople.app.mapping.MappingService;
import com.goodpeople.app.person.PersonFactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.goodpeople.app.medicine.MedicineController.API_V1;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicineController {

    public static final String API_V1 = "/api/v1/medicine";

    private final MedicineRepository medicineRepository;
    private final MappingService mappingService;
    private final PersonFactoryService personFactoryService;

    @GetMapping("/all")
    public List<Medicine> allPerson() {
        return medicineRepository.findAll();
    }

    @GetMapping("/medicine/{name}")
    public Optional<Medicine> personByName(@PathVariable("name") String name) {
        return medicineRepository.findByName(name);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Medicine create(@RequestBody Medicine medicine) {
        return medicineRepository.save(medicine);
    }

}
