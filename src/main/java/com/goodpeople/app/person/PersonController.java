package com.goodpeople.app.person;

import com.goodpeople.app.mapping.MappingService;
import com.goodpeople.app.mapping.RouteSummary;
import com.goodpeople.app.mapping.RouteType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.goodpeople.app.person.PersonController.API_V1_HELLO_WORLD;

@RestController
@RequestMapping(API_V1_HELLO_WORLD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    public static final String API_V1_HELLO_WORLD = "/api/v1/hello-world";

    private final PersonRepository personRepository;
    private final MappingService mappingService;

    @GetMapping("")
    public String greet() {
        return "Hello World!";
    }

    @GetMapping("/person/all")
    public List<Person> allPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{name}")
    public Optional<Person> personByName(@PathVariable("name") String name) {
        return personRepository.findByName(name);
    }

    @GetMapping("/route/{startPostcode}/{destPostcode}/{routeType}")
    public RouteSummary personByName(@PathVariable("startPostcode") String startPostcode,
                                     @PathVariable("destPostcode") String destPostcode,
                                     @PathVariable("routeType") String routeType) {
        return mappingService.getRouteSummaryBetween(startPostcode, destPostcode, RouteType.valueOf(routeType.toUpperCase()));
    }

}
