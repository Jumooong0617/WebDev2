package com.yole.carapp.service;

import com.yole.carapp.dto.CarDTO;
import com.yole.carapp.exceptions.ResourceNotFoundException;
import com.yole.carapp.models.Car;
import com.yole.carapp.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
    }

    public void update(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));

        car.setLicensePlateNumber(carDTO.getLicensePlateNumber());
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setColor(carDTO.getColor());
        car.setBodyType(carDTO.getBodyType());
        car.setEngineType(carDTO.getEngineType());
        car.setTransmission(carDTO.getTransmission());

        carRepository.save(car);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> searchCars(String keyword) {
        return carRepository
                .findByMakeContainingIgnoreCaseOrModelContainingIgnoreCaseOrLicensePlateNumberContainingIgnoreCase(
                        keyword, keyword, keyword
                );
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car save(CarDTO car){
        Car newCar = new Car();
        newCar.setLicensePlateNumber(car.getLicensePlateNumber());
        newCar.setMake(car.getMake());
        newCar.setModel(car.getModel());
        newCar.setYear(car.getYear());
        newCar.setColor(car.getColor());
        newCar.setBodyType(car.getBodyType());
        newCar.setEngineType(car.getEngineType());
        newCar.setTransmission(car.getTransmission());
        return carRepository.save(newCar);
    }

    public Car updateCar(Long id, CarDTO carDTO){
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", id));
        car.setLicensePlateNumber(carDTO.getLicensePlateNumber());
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setColor(carDTO.getColor());
        car.setBodyType(carDTO.getBodyType());
        car.setEngineType(carDTO.getEngineType());
        car.setTransmission(carDTO.getTransmission());
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        carRepository.delete(car);
    }
}
