package in.tailor.digi.main.repository;

import in.tailor.digi.model.Measurement;

import java.util.List;

public sealed interface MeasurementRepository permits MeasurementRepositoryImpl {
    int addMeasurement(String dressId, List<Measurement> measurementList);

    int updateMeasurement(List<Measurement> measurementList);
}
