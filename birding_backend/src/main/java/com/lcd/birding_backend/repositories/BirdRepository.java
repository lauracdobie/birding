package com.lcd.birding_backend.repositories;

import com.lcd.birding_backend.models.Bird;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirdRepository extends MongoRepository<Bird, String> {
}
