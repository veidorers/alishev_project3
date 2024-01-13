package com.example.springcourse.project3.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class MeasurementErrorResponse {
    private String message;
    private long timestamp;
}
