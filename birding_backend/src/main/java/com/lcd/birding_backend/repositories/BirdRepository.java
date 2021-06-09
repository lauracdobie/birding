package com.lcd.birding_backend.repositories;

import com.lcd.birding_backend.models.Bird;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdRepository extends MongoRepository<Bird, String> {
    List<Bird> findByComName(String name);
    List<Bird> findBySciName(String name);
//    @Query("{ 'locName':{$regex:?0,$options:'i'}")
    List<Bird> findByLocName(String name);
    List<Bird> findByComNameLikeOrderByComNameAsc(String name);
    List<Bird> findByLocNameLikeOrderByLocNameAsc(String name);
}
