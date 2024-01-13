package com.example.springcourse.project3.util;

import com.example.springcourse.project3.dto.SensorDto;
import com.example.springcourse.project3.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDto sensorDto = (SensorDto) target;

        var mayBeSensor = sensorsService.findByName(sensorDto.getName());
        if(mayBeSensor != null) {
            errors.rejectValue("name", "", "Sensor with this name already exists");
        }
    }
}
