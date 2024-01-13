package com.example.springcourse.project3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Min(value = -100, message = "Temperature should be greater than or equal to -100")
    @Max(value = 100, message = "Temperature should be less than or equal to 100")
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull(message = "Sensor should not be null")
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    private LocalDateTime measuredAt;
}
