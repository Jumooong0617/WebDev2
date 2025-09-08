package com.yole.carapp.repositories;

import com.yole.carapp.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByMakeContainingIgnoreCaseOrLicensePlateNumberContainingIgnoreCaseOrColorContainingIgnoreCaseOrBodyTypeContainingIgnoreCaseOrEngineTypeContainingIgnoreCaseOrTransmissionContainingIgnoreCase(
            String make,
            String licensePlate,
            String color,
            String bodyType,
            String engineType,
            String transmission
    );
}
