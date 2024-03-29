package com.example.springcourse.project3.service;

import com.example.springcourse.project3.model.Measurement;
import com.example.springcourse.project3.repository.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setMeasuredAt(LocalDateTime.now());
        measurementsRepository.save(measurement);
    }

    public long countRainyDays() {
        return measurementsRepository.countByRainingTrue();
    }
}
