package com.yole.carapp.repositories;

import com.yole.carapp.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    // ✅ Search by make, model, or license plate (case insensitive)
    List<Car> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCaseOrLicensePlateNumberContainingIgnoreCase(
            String make, String model, String licensePlateNumber
    );
}
