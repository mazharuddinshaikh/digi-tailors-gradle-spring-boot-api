package in.tailor.digi.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DigiTailorApiGatewayMainApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DigiTailorApiGatewayMainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Api gateway application started");
	}
}
