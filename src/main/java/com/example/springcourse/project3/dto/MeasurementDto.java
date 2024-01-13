package com.example.springcourse.project3.dto;

import com.example.springcourse.project3.model.Sensor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MeasurementDto {
    @NotNull
    @Min(value = -100, message = "Temperature should be greater than or equal to -100")
    @Max(value = 100, message = "Temperature should be less than or equal to 100")
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull(message = "Sensor should not be null")
    private Sensor sensor;
}
