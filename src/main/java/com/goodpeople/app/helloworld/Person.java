package com.goodpeople.app.helloworld;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "person")
public class Person {

    @Id
    private String id;
    private String name;
    private String country;
}
