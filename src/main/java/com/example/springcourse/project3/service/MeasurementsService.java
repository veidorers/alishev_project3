package com.example.springcourse.project3.service;

import com.example.springcourse.project3.model.Measurement;
import com.example.springcourse.project3.repository.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()));
        measurement.setMeasuredAt(LocalDateTime.now());
        measurementsRepository.save(measurement);
    }
}
