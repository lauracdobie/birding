package com.lcd.birding_backend.controllers;

import com.lcd.birding_backend.models.Bird;
import com.lcd.birding_backend.repositories.BirdRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BirdController {
    @Autowired
    BirdRepository birdRepository;

    @GetMapping (value = "/api/birds")
    public ResponseEntity<List<Bird>> getBirdsFromDB(
            @RequestParam (name="com-name-contains", required = false) String comNamePart,
            @RequestParam (name="loc-name-contains", required = false) String locNamePart,
            @RequestParam(name="loc-name", required = false) String locName
    ) {
        if (locNamePart != null){
            return new ResponseEntity<>(birdRepository.findByLocNameLikeOrderByLocNameAsc(locNamePart), HttpStatus.OK);
        }

        if (locName != null) {
            return new ResponseEntity<>(birdRepository.findByLocName(locName), HttpStatus.OK);
        }

        if (comNamePart != null){
            return new ResponseEntity<>(birdRepository.findByComNameLikeOrderByComNameAsc(comNamePart), HttpStatus.OK);
        }

        List<Bird> dbBirds = birdRepository.findAll();
        return new ResponseEntity<>(dbBirds, HttpStatus.OK);
    }

    @GetMapping (value = "api/birds/random-10")
    public ResponseEntity<List<Bird>> getRandomTenBirds() {
        List<Bird> dbBirds = birdRepository.findAll();
        Collections.shuffle(dbBirds);
        List<Bird> foundBirds = new ArrayList<>();

        int i = 0;

        while (foundBirds.size() < 10) {
            Bird randomBird = dbBirds.get(i);
            foundBirds.add(randomBird);
            i ++;
        }

        return new ResponseEntity<>(foundBirds, HttpStatus.OK);
    }

    @GetMapping (value = "api/birds/random")
    public ResponseEntity<List<Bird>> getRandomBird() {
        List<Bird> dbBirds = birdRepository.findAll();
        int max = dbBirds.size() -1;
        Random random = new Random();
        int randomIndex = random.nextInt((max - 0) + 0);
        List<Bird> foundBirds = new ArrayList<>();

        Bird randomBird = dbBirds.get(randomIndex);
        foundBirds.add(randomBird);
        return new ResponseEntity<>(foundBirds, HttpStatus.OK);
    }

//    @GetMapping (value = "api/birds/location-name/{locName}")
//    public ResponseEntity<List<Bird>> getBirdsByLocName(@PathVariable String locName) {
//        return new ResponseEntity<>(birdRepository.findByLocName(locName), HttpStatus.OK);
//    }

//    @GetMapping (value = "api/birds/random/{number}")
//    public ResponseEntity<List<Bird>> getRandomBirds(@RequestParam String number) {
//        List<Bird> dbBirds = birdRepository.findAll();
//        Collections.shuffle(dbBirds);
//        List<Bird> foundBirds = new ArrayList<>();
//        int convertedNumber = Integer.parseInt(number);
//        int i = 0;
//        while (i < convertedNumber - 1){
//            Bird randomBird = dbBirds.get(i);
//            foundBirds.add(randomBird);
//            i ++;
//        }
//
//        return new ResponseEntity<>(foundBirds, HttpStatus.OK);
//    }


}
