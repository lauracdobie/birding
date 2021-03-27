package com.lcd.birding_backend.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class API {
    @Value("${E_BIRD_API_KEY}")
    String apiKey;

    public HttpResponse<JsonNode> getAllBirds() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response
                = Unirest.get("https://api.ebird.org/v2/data/obs/GB-SCT/recent?back=30")
                .header("X-eBirdApiToken", apiKey)
                .asJson();

        System.out.println(response);
        return response;
    }
}
