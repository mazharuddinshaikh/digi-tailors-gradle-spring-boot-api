package in.tailor.digi.shop;

/**
 * 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class DigiTailorShopMainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DigiTailorShopMainApplication.class, args);
	}

}
