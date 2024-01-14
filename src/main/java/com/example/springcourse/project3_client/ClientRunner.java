package com.example.springcourse.project3_client;

import com.example.springcourse.project3.dto.MeasurementDto;
import com.example.springcourse.project3.dto.MeasurementsResponse;
import com.example.springcourse.project3.dto.SensorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ClientRunner {
    public static void main(String[] args) throws JsonProcessingException {
        var restTemplate = new RestTemplate();
//        registerSensor(restTemplate, "Sensor3");
        addNMeasurements(100, restTemplate);
        printMeasurements(restTemplate);



    }

    private static void registerSensor(RestTemplate restTemplate, String sensorName) {
        String url = "http://localhost:8080/sensor/registration";
        Map<String, String> sensorJsonData = new HashMap<>();
        sensorJsonData.put("name", sensorName);
        restTemplate.postForLocation(url, sensorJsonData);
    }

    private static void addNMeasurements(int amountMeasurements, RestTemplate restTemplate) throws JsonProcessingException {
        String url = "http://localhost:8080/measurements/add";
        Random random = new Random();
        var objectMapper = new ObjectMapper();
        for (int i = 0; i < amountMeasurements; i++) {
            double value = random.nextDouble(200.00) - 100;
            boolean raining = random.nextBoolean();
            var sensor = new SensorDto("Sensor");
            var measurementDto = new MeasurementDto(value, raining, sensor);

            HttpEntity<MeasurementDto> request = new HttpEntity<>(measurementDto);
            try {
                restTemplate.postForLocation(url, request);
                System.out.println("Success!");
            } catch(Exception e) {
                System.out.println("Error!!");
            }
        }
    }

    private static void printMeasurements(RestTemplate restTemplate) {
        String url = "http://localhost:8080/measurements";
        var measurementsResponse = restTemplate.getForObject(url, MeasurementsResponse.class);
        measurementsResponse.getMeasurements().forEach(measurementDto -> System.out.println(measurementDto.getValue() + " - " + measurementDto.getRaining() + " - " +  measurementDto.getSensor().getName()));
    }
}
