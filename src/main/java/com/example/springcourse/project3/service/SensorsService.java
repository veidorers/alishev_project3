package com.example.springcourse.project3.service;

import com.example.springcourse.project3.model.Sensor;
import com.example.springcourse.project3.repository.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

    public Sensor findByName(String name) {
        return sensorsRepository.findByName(name).orElse(null);
    }
}
