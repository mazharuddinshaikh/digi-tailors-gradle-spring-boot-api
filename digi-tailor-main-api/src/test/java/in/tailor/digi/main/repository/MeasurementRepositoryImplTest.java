package in.tailor.digi.main.repository;

import in.tailor.digi.model.Measurement;
import in.tailor.digi.model.MeasurementAttribute;
import in.tailor.digi.model.MeasurementAttributeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MeasurementRepositoryImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private MeasurementRepositoryImpl measurementRepository;

    @Test
    void addMeasurement_WithMeasurementAttributeType() {
        Measurement measurement = Measurement.builder()
                .measurementId("TEST_MEASUREMENT_ID")
                .measurementAttribute(MeasurementAttribute.builder().build())
                .measurementAttributeType(MeasurementAttributeType.builder().build())
                .measurementValue(new BigDecimal("23.00"))
                .measurementUnit("INCH")
                .build();

        Mockito.when(jdbcTemplate.batchUpdate(Mockito.anyString(), Mockito.any(BatchPreparedStatementSetter.class)))
                .thenAnswer(invocation -> {
                    String sql = invocation.getArgument(0);
                    BatchPreparedStatementSetter setter = invocation.getArgument(1);
                    int batchSize = setter.getBatchSize();
                    int[] result = new int[batchSize];
                    for (int i = 0; i < batchSize; i++) {
                        setter.setValues(Mockito.mock(PreparedStatement.class), i);
                        result[i] = 1;
                    }
                    return result;
                });
        var result = measurementRepository.addMeasurement("TEST_DRESS_ID", List.of(measurement));
        Assertions.assertEquals(1, result);
    }

    @Test
    void addMeasurement_WithoutMeasurementAttributeType() {
        Measurement measurement = Measurement.builder()
                .measurementId("TEST_MEASUREMENT_ID")
                .measurementAttribute(MeasurementAttribute.builder().build())
                .measurementValue(new BigDecimal("23.00"))
                .measurementUnit("INCH")
                .build();

        Mockito.when(jdbcTemplate.batchUpdate(Mockito.anyString(), Mockito.any(BatchPreparedStatementSetter.class)))
                .thenAnswer(invocation -> {
                    String sql = invocation.getArgument(0);
                    BatchPreparedStatementSetter setter = invocation.getArgument(1);
                    int batchSize = setter.getBatchSize();
                    int[] result = new int[batchSize];
                    for (int i = 0; i < batchSize; i++) {
                        setter.setValues(Mockito.mock(PreparedStatement.class), i);
                        result[i] = 1;
                    }
                    return result;
                });
        var result = measurementRepository.addMeasurement("TEST_DRESS_ID", List.of(measurement));
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateMeasurement_WithMeasurementAttributeType() {
        Measurement measurement = Measurement.builder()
                .measurementId("TEST_MEASUREMENT_ID")
                .measurementAttribute(MeasurementAttribute.builder().build())
                .measurementAttributeType(MeasurementAttributeType.builder().build())
                .measurementValue(new BigDecimal("23.00"))
                .measurementUnit("INCH")
                .build();

        Mockito.when(jdbcTemplate.batchUpdate(Mockito.anyString(), Mockito.any(BatchPreparedStatementSetter.class)))
                .thenAnswer(invocation -> {
                    String sql = invocation.getArgument(0);
                    BatchPreparedStatementSetter setter = invocation.getArgument(1);
                    int batchSize = setter.getBatchSize();
                    int[] result = new int[batchSize];
                    for (int i = 0; i < batchSize; i++) {
                        setter.setValues(Mockito.mock(PreparedStatement.class), i);
                        result[i] = 1;
                    }
                    return result;
                });
        var result = measurementRepository.updateMeasurement(List.of(measurement));
        Assertions.assertEquals(1, result);
    }

    @Test
    void updateMeasurement_WithoutMeasurementAttributeType() {
        Measurement measurement = Measurement.builder()
                .measurementId("TEST_MEASUREMENT_ID")
                .measurementAttribute(MeasurementAttribute.builder().build())
                .measurementValue(new BigDecimal("23.00"))
                .measurementUnit("INCH")
                .build();

        Mockito.when(jdbcTemplate.batchUpdate(Mockito.anyString(), Mockito.any(BatchPreparedStatementSetter.class)))
                .thenAnswer(invocation -> {
                    String sql = invocation.getArgument(0);
                    BatchPreparedStatementSetter setter = invocation.getArgument(1);
                    int batchSize = setter.getBatchSize();
                    int[] result = new int[batchSize];
                    for (int i = 0; i < batchSize; i++) {
                        setter.setValues(Mockito.mock(PreparedStatement.class), i);
                        result[i] = 1;
                    }
                    return result;
                });
        var result = measurementRepository.updateMeasurement(List.of(measurement));
        Assertions.assertEquals(1, result);
    }
}