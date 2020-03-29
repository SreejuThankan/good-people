package com.goodpeople.app.helloworld;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.goodpeople.app.helloworld.HelloWorldController.API_V1_HELLO_WORLD;

@RestController
@RequestMapping(API_V1_HELLO_WORLD)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloWorldController {

    public static final String API_V1_HELLO_WORLD = "/api/v1/hello-world";

    private final PersonRepository personRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String greet() {
        return "Hello World!";
    }

    @GetMapping("/person/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> allPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> personByName(@PathVariable("name") String name) {
        return personRepository.findByName(name);
    }
}
