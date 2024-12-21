/**
 * 
 */
package in.tailor.digi.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DigiTailorInvoiceApiMainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DigiTailorInvoiceApiMainApplication.class, args);
	}

}
