package com.goodpeople.app.person;

public interface PersonFactoryService {

    Person create(CreatePersonDto createPersonDto);

    Person createAndSave(CreatePersonDto createPersonDto);
}
