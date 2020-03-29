package com.goodpeople.app.medicine;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
    Optional<Medicine> findByName(String name);
}
