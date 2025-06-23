package in.tailor.digi.main.repository;

import in.tailor.digi.model.Measurement;
import in.tailor.digi.utils.DtsDateTimeUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
@Setter
public non-sealed class MeasurementRepositoryImpl implements MeasurementRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addMeasurement(String dressId, List<Measurement> measurementList) {
        final String sql = "INSERT INTO DTS_MEASUREMENT (MEASUREMENT_ID, MEASUREMENT_ATTRIBUTE_ID, MEASUREMENT_ATTRIBUTE_TYPE_ID, " +
                "DRESS_ID, MEASUREMENT_VALUE, MEASUREMENT_UNIT, CREATED_AT) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int[] result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(@NonNull PreparedStatement ps, int i) throws SQLException {
                final Measurement measurement = measurementList.get(i);
                ps.setString(1, measurement.getMeasurementId());
                ps.setString(2, measurement.getMeasurementAttribute().getAttributeId());
                ps.setString(3, measurement.getMeasurementAttributeType() != null ? measurement.getMeasurementAttributeType().getAttributeTypeId() : null);
                ps.setString(4, dressId);
                ps.setBigDecimal(5, measurement.getMeasurementValue());
                ps.setString(6, "INCH");
                ps.setObject(7, DtsDateTimeUtil.getIndianCurrentDateTime());
            }

            @Override
            public int getBatchSize() {
                return measurementList.size();
            }
        });
        return result.length;
    }

    @Override
    public int updateMeasurement(List<Measurement> measurementList) {
        final String sql = "UPDATE DTS_MEASUREMENT SET MEASUREMENT_ATTRIBUTE_TYPE_ID = ?, MEASUREMENT_VALUE = ?, UPDATED_AT = ? WHERE MEASUREMENT_ID = ?";
        int[] result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(@NonNull PreparedStatement ps, int i) throws SQLException {
                Measurement measurement = measurementList.get(i);
                ps.setString(1, measurement.getMeasurementAttributeType() != null ? measurement.getMeasurementAttributeType().getAttributeTypeId() : null);
                ps.setBigDecimal(2, measurement.getMeasurementValue());
                ps.setObject(3, DtsDateTimeUtil.getIndianCurrentDateTime());
                ps.setString(4, measurement.getMeasurementId());
            }

            @Override
            public int getBatchSize() {
                return measurementList.size();
            }
        });
        return result.length;
    }
}
