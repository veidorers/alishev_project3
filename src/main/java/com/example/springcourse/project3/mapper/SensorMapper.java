package com.example.springcourse.project3.mapper;

import com.example.springcourse.project3.dto.SensorDto;
import com.example.springcourse.project3.model.Sensor;
import com.example.springcourse.project3.service.SensorsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {
    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;

    @Autowired
    public SensorMapper(ModelMapper modelMapper, SensorsService sensorsService) {
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
    }

    public Sensor convertDtoToSensor(SensorDto sensorDto) {
         return modelMapper.map(sensorDto, Sensor.class);
    }

    public SensorDto convertSensorToDto(Sensor sensor) {
        return modelMapper.map(sensor, SensorDto.class);
    }
}
