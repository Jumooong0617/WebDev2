package com.yole.carapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String make;
    String model;
    int year;
    String licensePlateNumber;
    String color;
    String bodyType;
    String engineType;
    String transmission;

    public Car(Integer id, String make, String model, int year,
               String licensePlateNumber, String color,
               String bodyType, String engineType, String transmission) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlateNumber = licensePlateNumber;
        this.color = color;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.transmission = transmission;
    }

    public Car() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) { this.id = id; }

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {  // ✅ Getter
        return model;
    }
    public void setModel(String model) {  // ✅ Setter
        this.model = model;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getBodyType() {
        return bodyType;
    }
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngineType() {
        return engineType;
    }
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}
