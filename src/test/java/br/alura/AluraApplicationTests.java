package br.alura;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertNull;

@Profile("local")
@SpringBootTest
class AluraApplicationTests {

	@Test
	void contextLoads() {
		assertNull(null);
	}

}
