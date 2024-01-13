package com.example.springcourse.project3.controller;

import com.example.springcourse.project3.model.Sensor;
import com.example.springcourse.project3.service.SensorsService;
import com.example.springcourse.project3.util.SensorErrorResponse;
import com.example.springcourse.project3.util.SensorNotCreatedException;
import com.example.springcourse.project3.util.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorsController {
    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid Sensor sensor,
                                          BindingResult bindingResult) {
        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors()) {
            var errorMessage = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }

        sensorsService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        var response = SensorErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
