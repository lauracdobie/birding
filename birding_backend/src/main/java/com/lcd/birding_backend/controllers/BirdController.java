package com.lcd.birding_backend.controllers;

import com.lcd.birding_backend.models.Bird;
import com.lcd.birding_backend.repositories.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class BirdController {
    @Autowired
    BirdRepository birdRepository;

    @GetMapping (value = "/api/birds")
    public ResponseEntity<List<Bird>> getBirdsFromDB() {
        List<Bird> dbBirds = birdRepository.findAll();
        return new ResponseEntity<>(dbBirds, HttpStatus.OK);
    }

    @GetMapping (value = "api/birds/random")
    public ResponseEntity<Bird> getRandomBird() {
        List<Bird> dbBirds = birdRepository.findAll();
        Collections.shuffle(dbBirds);
        Bird randomBird = dbBirds.get(0);
        return new ResponseEntity<>(randomBird, HttpStatus.OK);
    }
}
