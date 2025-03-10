package com.propensi.genius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class GeniusApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        System.setProperty("GENIUS_APP_JWT_SECRET", dotenv.get("GENIUS_APP_JWT_SECRET"));
        System.setProperty("GENIUS_APP_JWT_EXPIRATION_MS", dotenv.get("GENIUS_APP_JWT_EXPIRATION_MS"));
		SpringApplication.run(GeniusApplication.class, args);
	}

}

