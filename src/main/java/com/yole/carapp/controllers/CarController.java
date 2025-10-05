package com.yole.carapp.controllers;

import com.yole.carapp.dto.CarDTO;
import com.yole.carapp.models.Car;
import com.yole.carapp.repositories.CarRepository;
import com.yole.carapp.service.CarService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }

    @PostMapping("/cars")
    public Car newCar(@Valid @RequestBody CarDTO car) {
        return carService.save(car);
    }

    @PutMapping("/car/{id}")
    public Car updateCar(@PathVariable Long id, @Valid @RequestBody CarDTO car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
