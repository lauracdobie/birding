package com.lcd.birding_backend.components;

import com.lcd.birding_backend.api.API;
import com.lcd.birding_backend.models.Bird;
import com.lcd.birding_backend.models.BirdPayload;
import com.lcd.birding_backend.repositories.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    BirdRepository birdRepository;

    @Autowired
    API api;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BirdPayload> birdsFromAPI = api.getAllBirds();

        List<Bird> birdObjects = birdsFromAPI.stream().map(bird -> new Bird(bird.getComName(), bird.getSciName(), bird.getLocName(), bird.getObsDt(), bird.getHowMany(), bird.getLat(), bird.getLng())).collect(Collectors.toList());

        birdRepository.saveAll(birdObjects);

    }
}
