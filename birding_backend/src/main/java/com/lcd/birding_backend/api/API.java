package com.lcd.birding_backend.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcd.birding_backend.models.BirdPayload;
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

    public String getAllBirds() {
        String url = "https://api.ebird.org/v2/data/obs/GB-SCT/recent?back=30";
        ObjectMapper objectMapper = new ObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
// Add the Jackson message converter
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-eBirdApiToken", apiKey);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String responseBody = response.getBody();

        System.out.println(responseBody);
        return responseBody;
    };

//    public HttpResponse<JsonNode> getAllBirds() throws UnirestException {
//        Unirest.setTimeouts(0, 0);
//        HttpResponse<JsonNode> response
//                = Unirest.get("https://api.ebird.org/v2/data/obs/GB-SCT/recent?back=30")
//                .header("X-eBirdApiToken", apiKey)
//                .asJson();
//
//        System.out.println(response.getBody());
//        return response;
//    }
}
