package com.goodpeople.app.person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {
    Optional<Person> findByFirstName(String firstName);

    @Query("{'address.postCodeDetails.admin_district': '$0', 'ownedMedicines.medicine._id': ObjectId('$1')}")
    List<Person> findPeopleInCityWithOwnedMedicine(String city, String ownedMedicineID);
}
