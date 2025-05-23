/**
 * 
 */
package in.tailor.digi.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DigiTailorStatsApiMainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DigiTailorStatsApiMainApplication.class, args);
	}

}
