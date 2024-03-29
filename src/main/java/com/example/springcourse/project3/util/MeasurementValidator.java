package com.example.springcourse.project3.util;

import com.example.springcourse.project3.dto.MeasurementDto;
import com.example.springcourse.project3.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDto measurementDto = (MeasurementDto) target;

        //if measurement.getSensor is null it'll be validated via @NotNull annotation
        if(measurementDto.getSensor() != null) {
            var mayBeSensor = sensorsService.findByName(measurementDto.getSensor().getName());
            if (mayBeSensor == null) {
                errors.rejectValue("sensor", "", "Sensor with this name doesn't exist!");
            }
        }
    }
}
