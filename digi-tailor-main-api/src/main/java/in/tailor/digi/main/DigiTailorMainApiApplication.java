/**
 * 
 */

package in.tailor.digi.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DigiTailorMainApiApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DigiTailorMainApiApplication.class, args);
	}

}
