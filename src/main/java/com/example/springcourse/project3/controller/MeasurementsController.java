package com.example.springcourse.project3.controller;

import com.example.springcourse.project3.dto.MeasurementDto;
import com.example.springcourse.project3.mapper.MeasurementMapper;
import com.example.springcourse.project3.service.MeasurementsService;
import com.example.springcourse.project3.util.MeasurementErrorResponse;
import com.example.springcourse.project3.util.MeasurementNotCreatedException;
import com.example.springcourse.project3.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final MeasurementValidator measurementValidator;
    private final MeasurementMapper measurementMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, MeasurementValidator measurementValidator, MeasurementMapper measurementMapper) {
        this.measurementsService = measurementsService;
        this.measurementValidator = measurementValidator;
        this.measurementMapper = measurementMapper;
    }

    @GetMapping("/rainyDaysCount")
    public long countRainyDays() {
        return measurementsService.countRainyDays();
    }

    @GetMapping
    public List<MeasurementDto> findAll() {
        return measurementsService.findAll().stream()
                .map(measurementMapper::convertMeasurementToDto).collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDto measurementDto,
                                          BindingResult bindingResult) {
        measurementValidator.validate(measurementDto, bindingResult);
        if(bindingResult.hasErrors()) {
            var errorMessage = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }
            throw new MeasurementNotCreatedException(errorMessage.toString());
        }
        measurementsService.save(measurementMapper.convertDtoToMeasurement(measurementDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        var response = MeasurementErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
