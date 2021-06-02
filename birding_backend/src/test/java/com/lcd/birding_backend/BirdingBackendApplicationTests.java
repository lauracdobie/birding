package com.lcd.birding_backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lcd.birding_backend.api.API;
import com.lcd.birding_backend.models.Bird;
import com.lcd.birding_backend.repositories.BirdRepository;
import com.lcd.birding_backend.services.LRUCache;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
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

	Map<String, String> testCache = new LRUCache<String, String>(4);

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
	public void canCallAPI() throws JsonProcessingException {
		api.getAllBirds();
	}

	@Test
	public void canFindBirdByComName() {
		List<Bird> foundBird = birdRepository.findByComName("Common Goldeneye");
		assertEquals("60904b809d8f880773852d7a", foundBird.get(0).getId());
	}

	@Test
	public void canFindBirdBySciName() {
		List<Bird> foundBird = birdRepository.findBySciName("Anas crecca");
		assertEquals("60904b809d8f880773852d7b", foundBird.get(0).getId());
	}

	@Test
	public void canFindBirdByLocation() {
		List<Bird> foundBird = birdRepository.findByLocName("Loch of Kinnordy");
		assertEquals(7, foundBird.size());
	}

	@Test
	public void cacheOnlyRetainsFourItems () {
		testCache.put("hello", "buongiorno");
		testCache.put("goodbye", "arrivederci");
		testCache.put("cat", "gatto");
		testCache.put("dog", "cane");
		System.out.println(testCache.toString());
		testCache.put("ice cream", "gelato");
		System.out.println(testCache.toString());
		assertEquals(4, testCache.size());
		assertFalse(testCache.containsKey("hello"));
	}

}
