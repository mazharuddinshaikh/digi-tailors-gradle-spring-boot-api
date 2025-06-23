package in.tailor.digi.main.service;

import in.tailor.digi.model.Measurement;

import java.util.List;

public sealed interface MeasurementService permits MeasurementServiceImpl {
    List<Measurement> addMeasurement(String dressId, List<Measurement> measurementList);

    List<Measurement> updateMeasurement(List<Measurement> measurementList);
}
