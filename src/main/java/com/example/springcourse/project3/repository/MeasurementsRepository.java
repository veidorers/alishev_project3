package com.example.springcourse.project3.repository;

import com.example.springcourse.project3.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    long countByRainingTrue();
}
