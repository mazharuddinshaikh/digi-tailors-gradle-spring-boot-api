/**
 * 
 */
package in.tailor.digi.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DigiTailorCustomerApiMainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DigiTailorCustomerApiMainApplication.class, args);
	}

}
