package com.goodpeople.app.person;

import com.goodpeople.app.mapping.PostCodeIoService;
import com.goodpeople.app.mapping.RouteType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonFactoryServiceImpl implements PersonFactoryService {

    private final PostCodeIoService postCodeIoService;
    private final PersonRepository personRepository;

    @Override
    public Person create(CreatePersonDto createPersonDto) {
        EnumSet<RouteType> routeTypeEnumSet = createPersonDto.getRouteTypes().stream()
                                                             .map(String::toUpperCase)
                                                             .map(RouteType::valueOf)
                                                             .collect(Collectors.toCollection(() -> EnumSet.noneOf(RouteType.class)));

        return Person.builder()
                     .firstName(createPersonDto.getFirstName())
                     .lastName(createPersonDto.getLastName())
                     .email(createPersonDto.getEmail())
                     .phoneNumber(createPersonDto.getPhoneNo())
                     .address(new Address(createPersonDto.getAddressLine1(),
                                          createPersonDto.getAddressLine2(),
                                          postCodeIoService.getDetailsForPostCode(createPersonDto.getPostCode())))
                     .routeTypes(routeTypeEnumSet)
                     .build();
    }

    @Override
    public Person createAndSave(CreatePersonDto createPersonDto) {
        return personRepository.save(create(createPersonDto));
    }
}
