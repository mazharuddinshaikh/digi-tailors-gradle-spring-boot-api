package in.tailor.digi.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DigiTailorEurekaServerMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigiTailorEurekaServerMainApplication.class, args);
	}

}
