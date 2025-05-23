/**
 * 
 */

package in.tailor.digi.dress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DigiTailorDressApiMainApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DigiTailorDressApiMainApplication.class, args);
	}

}
