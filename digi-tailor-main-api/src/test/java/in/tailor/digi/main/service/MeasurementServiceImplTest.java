package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.MeasurementRepositoryImpl;
import in.tailor.digi.model.Measurement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MeasurementServiceImplTest {
    @Mock
    private MeasurementRepositoryImpl measurementRepository;
    @InjectMocks
    private MeasurementServiceImpl measurementService;

    @Test
    void addMeasurement_WhenSuccessFull() {
        String dressId = "TEST_DRESS_ID";
        Measurement measurement = new Measurement();
        measurement.setMeasurementValue(new BigDecimal("32.00"));
        List<Measurement> measurementList = new ArrayList<>();
        measurementList.add(measurement);
        Mockito.when(measurementRepository.addMeasurement(Mockito.anyString(), Mockito.anyList())).thenReturn(1);
        var result = measurementService.addMeasurement(dressId, measurementList);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void addMeasurement_WhenSuccessFailed() {
        String dressId = "TEST_DRESS_ID";
        Measurement measurement = new Measurement();
        measurement.setMeasurementValue(new BigDecimal("32.00"));
        List<Measurement> measurementList = new ArrayList<>();
        measurementList.add(measurement);
        Mockito.when(measurementRepository.addMeasurement(Mockito.anyString(), Mockito.anyList())).thenReturn(0);
        var result = measurementService.addMeasurement(dressId, measurementList);
        Assertions.assertNull(result);
    }

    @Test
    void updateMeasurement_WhenSuccessFull() {
        Measurement measurement = new Measurement();
        measurement.setMeasurementValue(new BigDecimal("32.00"));
        List<Measurement> measurementList = new ArrayList<>();
        measurementList.add(measurement);
        Mockito.when(measurementRepository.updateMeasurement(Mockito.anyList())).thenReturn(1);
        var result = measurementService.updateMeasurement(measurementList);
        Assertions.assertFalse(result.isEmpty());
    }

}