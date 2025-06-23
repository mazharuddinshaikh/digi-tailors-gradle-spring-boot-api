package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.MeasurementRepository;
import in.tailor.digi.model.Measurement;
import in.tailor.digi.utils.DtsCodeUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
@Slf4j
public non-sealed class MeasurementServiceImpl implements MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    @Transactional
    public List<Measurement> addMeasurement(String dressId, List<Measurement> measurementList) {
        measurementList = measurementList.stream()
                .map(measurement -> {
                    measurement.setMeasurementId(DtsCodeUtils.generateMeasurementId());
                    return measurement;
                }).collect(Collectors.toList());
        final int insertCount = measurementRepository.addMeasurement(dressId, measurementList);
        return insertCount > 0 ? measurementList : null;
    }

    @Override
    @Transactional
    public List<Measurement> updateMeasurement(List<Measurement> measurementList) {
        measurementRepository.updateMeasurement(measurementList);
        return measurementList;
    }
}
