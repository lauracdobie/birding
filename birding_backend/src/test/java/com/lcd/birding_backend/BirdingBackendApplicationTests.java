package com.lcd.birding_backend;

import com.lcd.birding_backend.api.API;
import com.lcd.birding_backend.models.Bird;
import com.lcd.birding_backend.repositories.BirdRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BirdingBackendApplicationTests {

	@Autowired
	BirdRepository birdRepository;

	@Autowired
	API api;

	@Value("${E_BIRD_API_KEY}")
	String apiKey;

	@Test
	void contextLoads() {
	}

	@Test
	public void canSaveBirdToDB(){
		Bird bird = new Bird("Hooded Crow", "Corvus cornix", "улица Старикова, Chundzha KZ-Almaty (43.5309,79.4551)", "2020-01-21 16:35", 1, 43.530936, 79.455132);
		birdRepository.save(bird);
	}

	@Test
	public void canAccessAPIKey() {
		System.out.println(apiKey);
		assertNotNull(apiKey);
	}

	@Test
	public void shouldReturnStatusOkay() throws UnirestException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<JsonNode> response
				= Unirest.get("https://api.ebird.org/v2/data/obs/KZ/recent")
				.header("X-eBirdApiToken", apiKey)
				.asJson();

		assertNotNull(response.getBody());
		assertEquals(200, response.getStatus());
	}

	@Test
	public void canCallAPI() throws UnirestException {
		api.getAllBirds();
	}

}
