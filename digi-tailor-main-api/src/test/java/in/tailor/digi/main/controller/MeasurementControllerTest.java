package in.tailor.digi.main.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MeasurementControllerTest {
    @InjectMocks
    private MeasurementController measurementController;

    @Test
    public void testApi_ShouldReturnOk() {
        var result = measurementController.testApi();
        Assertions.assertEquals("Measurement Api working", result.getBody());
    }
}