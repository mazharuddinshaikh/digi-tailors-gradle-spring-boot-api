/**
 * 
 */
package in.tailor.digi.main.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("measurement")
public class MeasurementController {
	
	@GetMapping("/test")
	@Operation(summary = "Test If Application is running")
	public ResponseEntity<String> testApi() {
		return ResponseEntity.ok("Measurement Api working");
	}

}
