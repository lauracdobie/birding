package com.lcd.birding_backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcd.birding_backend.models.BirdPayload;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class API {
    @Value("${E_BIRD_API_KEY}")
    String apiKey;

    public BirdPayload[] getAllBirds() throws JsonProcessingException, UnirestException {
        String url = "https://api.ebird.org/v2/data/obs/GB-SCT/recent?back=30";
        ObjectMapper objectMapper = new ObjectMapper();

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response
                = Unirest.get("https://api.ebird.org/v2/data/obs/GB-SCT/recent?back=30")
                .header("X-eBirdApiToken", apiKey)
                .asString();

        String responseBody = response.getBody();
        BirdPayload[] birdsFromAPI = new BirdPayload[0];
        birdsFromAPI = objectMapper.readValue(responseBody, BirdPayload[].class);

        return birdsFromAPI;
        }
    }
