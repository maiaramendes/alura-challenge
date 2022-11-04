package br.alura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile({"local", "prod"})
@SpringBootApplication
public class AluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraApplication.class, args);
	}

}
