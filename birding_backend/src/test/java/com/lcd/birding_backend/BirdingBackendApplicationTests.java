package com.lcd.birding_backend;

import com.lcd.birding_backend.models.Bird;
import com.lcd.birding_backend.repositories.BirdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BirdingBackendApplicationTests {

	@Autowired
	BirdRepository birdRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void canSaveBirdToDB(){
		Bird bird = new Bird("Hooded Crow", "Corvus cornix", "улица Старикова, Chundzha KZ-Almaty (43.5309,79.4551)", "2020-01-21 16:35", 1, 43.530936, 79.455132);
		birdRepository.save(bird);
	}

}
