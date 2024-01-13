package com.example.springcourse.project3.mapper;

import com.example.springcourse.project3.dto.MeasurementDto;
import com.example.springcourse.project3.model.Measurement;
import com.example.springcourse.project3.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementMapper {
    private final SensorMapper sensorMapper;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementMapper(SensorMapper sensorMapper, SensorsService sensorsService) {
        this.sensorMapper = sensorMapper;
        this.sensorsService = sensorsService;
    }

    public Measurement convertDtoToMeasurement(MeasurementDto measurementDto) {
        var measurement = new Measurement();
        measurement.setValue(measurementDto.getValue());
        measurement.setRaining(measurementDto.getRaining());
        measurement.setSensor(sensorsService.findByName(measurementDto.getSensor().getName()));
        return measurement;
    }

    public MeasurementDto convertMeasurementToDto(Measurement measurement) {
        var measurementDto = new MeasurementDto();
        measurementDto.setValue(measurement.getValue());
        measurementDto.setRaining(measurement.getRaining());
        measurementDto.setSensor(sensorMapper.convertSensorToDto(measurement.getSensor()));
        return measurementDto;
    }
}
