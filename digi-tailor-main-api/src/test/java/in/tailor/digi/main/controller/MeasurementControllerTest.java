package in.tailor.digi.main.controller;

import in.tailor.digi.main.service.MeasurementServiceImpl;
import in.tailor.digi.model.Dress;
import in.tailor.digi.model.DtsException;
import in.tailor.digi.model.Measurement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MeasurementControllerTest {
    @Mock
    private MeasurementServiceImpl measurementService;
    @InjectMocks
    private MeasurementController measurementController;

    @Test
    public void testApi_ShouldReturnOk() {
        var result = measurementController.testApi();
        Assertions.assertEquals("Measurement Api working", result.getBody());
    }

    @Test
    void shouldReturnSuccessResponseWhenMeasurementIsAdded() throws DtsException {
        Dress dress = new Dress();
        dress.setDressId("TEST_DRESS_ID");
        Measurement measurement = new Measurement();
        dress.setMeasurementList(List.of(measurement));
        // Arrange
        Mockito.when(measurementService.addMeasurement(Mockito.anyString(), Mockito.anyList())).thenReturn(List.of(new Measurement()));

        // Act
        ResponseEntity<String> response = measurementController.addMeasurement(dress);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Measurement added successfully", response.getBody());
    }

    @Test
    void shouldReturnSuccessResponseWhenMeasurementNotAdded() throws DtsException {
        Dress dress = new Dress();
        dress.setDressId("TEST_DRESS_ID");
        Measurement measurement = new Measurement();
        dress.setMeasurementList(List.of(measurement));
        // Arrange
        Mockito.when(measurementService.addMeasurement(Mockito.anyString(), Mockito.anyList())).thenReturn(List.of());
// Act
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                measurementController.addMeasurement(dress)
        );

        // Assert
        Assertions.assertEquals(403, thrown.getResponse().getHttpStatus());
        Mockito.verify(measurementService).addMeasurement(dress.getDressId(), dress.getMeasurementList());
    }

    @Test
    void shouldReturnSuccessResponseWhenMeasurementIsUpdated() throws DtsException {
        Dress dress = new Dress();
        dress.setDressId("TEST_DRESS_ID");
        Measurement measurement = new Measurement();
        dress.setMeasurementList(List.of(measurement));
        // Arrange
        Mockito.when(measurementService.updateMeasurement(Mockito.anyList())).thenReturn(List.of(new Measurement()));

        // Act
        ResponseEntity<String> response = measurementController.updateMeasurement(dress);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Measurement updated successfully", response.getBody());
    }

    @Test
    void shouldReturnSuccessResponseWhenMeasurementNotUpdated() throws DtsException {
        Dress dress = new Dress();
        dress.setDressId("TEST_DRESS_ID");
        Measurement measurement = new Measurement();
        dress.setMeasurementList(List.of(measurement));
        // Arrange
        Mockito.when(measurementService.updateMeasurement(Mockito.anyList())).thenReturn(List.of());
// Act
        DtsException thrown = Assertions.assertThrows(DtsException.class, () ->
                measurementController.updateMeasurement(dress)
        );

        // Assert
        Assertions.assertEquals(403, thrown.getResponse().getHttpStatus());
        Mockito.verify(measurementService).updateMeasurement(dress.getMeasurementList());
    }
}