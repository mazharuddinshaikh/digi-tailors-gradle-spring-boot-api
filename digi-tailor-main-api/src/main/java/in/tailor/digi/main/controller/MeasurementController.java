/**
 *
 */
package in.tailor.digi.main.controller;

import in.tailor.digi.main.service.MeasurementService;
import in.tailor.digi.model.Dress;
import in.tailor.digi.model.DtsApiResponse;
import in.tailor.digi.model.DtsException;
import in.tailor.digi.model.Measurement;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("measurement")
@Setter
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/test")
    @Operation(summary = "Test If Application is running")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("Measurement Api working");
    }

    @PostMapping("/v1")
    @Operation(summary = "Add measurement")
    public ResponseEntity<String> addMeasurement(@RequestBody Dress dress) throws DtsException {
        List<Measurement> measurementList = measurementService.addMeasurement(dress.getDressId(), dress.getMeasurementList());
        if (!CollectionUtils.isEmpty(measurementList)) {
            return ResponseEntity.ok("Measurement added successfully");
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());
    }

    @PutMapping("/v1")
    @Operation(summary = "Update measurement")
    public ResponseEntity<String> updateMeasurement(@RequestBody Dress dress) throws DtsException {
        List<Measurement> measurementList = measurementService.updateMeasurement(dress.getMeasurementList());
        if (!CollectionUtils.isEmpty(measurementList)) {
            return ResponseEntity.ok("Measurement updated successfully");
        }
        throw new DtsException(DtsApiResponse.<String>builder().message("Something went wrong. Please retry")
                .httpStatus(HttpStatus.FORBIDDEN.value()).build());

    }
}
