package com.lcd.birding_backend.controllers;

import com.lcd.birding_backend.models.Bird;
import com.lcd.birding_backend.repositories.BirdRepository;
import com.lcd.birding_backend.services.Capitaliser;
import com.lcd.birding_backend.services.LRUCache;
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

    private Map<String, List<Bird>> birdNameNest;

    public BirdController() {
        this.birdNameNest = new LRUCache<>(6);
    }

    Capitaliser capitaliser = new Capitaliser();

    @GetMapping (value = "/api/birds")
    public ResponseEntity<List<Bird>> getBirdsFromDB(
            @RequestParam (name="com-name-contains", required = false) String comNamePart,
            @RequestParam (name="loc-name-contains", required = false) String locNamePart,
            @RequestParam(name="loc-name", required = false) String locName
    ) {
        if (locNamePart != null){
            String capitalisedLocation = capitaliser.capitaliseEachWord(locNamePart);
            return new ResponseEntity<>(birdRepository.findByLocNameLikeOrderByLocNameAsc(capitalisedLocation), HttpStatus.OK);
        }

        if (locName != null) {
            String capitalisedLocation = capitaliser.capitaliseEachWord(locName);
            return new ResponseEntity<>(birdRepository.findByLocName(capitalisedLocation), HttpStatus.OK);
        }

        if (comNamePart != null){
            String capitalisedComName = capitaliser.capitaliseEachWord(comNamePart);
            if (birdNameNest.containsKey(capitalisedComName)) {
                return new ResponseEntity<>(birdNameNest.get(capitalisedComName), HttpStatus.OK);
            }
            birdNameNest.put(capitalisedComName, birdRepository.findByComNameLikeOrderByComNameAsc(capitalisedComName));
            System.out.println(birdNameNest);
            return new ResponseEntity<>(birdRepository.findByComNameLikeOrderByComNameAsc(capitalisedComName), HttpStatus.OK);
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
    public ResponseEntity<?> getRandomBird(
            @RequestParam (name = "number", required = false) String number
    ) {
        List<Bird> dbBirds = birdRepository.findAll();
        int convertedNumber = Integer.parseInt(number);
        if(number != null && convertedNumber <= dbBirds.size()) {
            Collections.shuffle(dbBirds);
            List<Bird> foundBirds = new ArrayList<>();

            int i = 0;
            while (i < convertedNumber){
                Bird randomBird = dbBirds.get(i);
                foundBirds.add(randomBird);
                i ++;
            }

            return new ResponseEntity<>(foundBirds, HttpStatus.OK);
        }

        if (number != null && convertedNumber > dbBirds.size()){
            String errorMessage = "There aren't that many birds in the database!";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        int max = dbBirds.size() -1;
        Random random = new Random();
        int randomIndex = random.nextInt((max - 0) + 0);
        List<Bird> foundBirds = new ArrayList<>();

        Bird randomBird = dbBirds.get(randomIndex);
        return new ResponseEntity<>(randomBird, HttpStatus.OK);
    }
}
