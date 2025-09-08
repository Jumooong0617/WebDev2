package com.yole.carapp.dto;

import jakarta.validation.constraints.*;

public class CarDTO {

    private int id;

    @NotBlank(message = "Make is required")
    @Size(min = 2, max = 50, message = "Make must be between 2 and 50 characters")
    private String make;

    @NotBlank(message = "Model is required")
    @Size(min = 1, max = 50, message = "Model must be between 1 and 50 characters")
    private String model;

    @Min(value = 1900, message = "Year must be at least 1900")
    @Max(value = 2100, message = "Year cannot exceed 2100")
    private int year;

    @NotBlank(message = "Color is required")
    @Size(min = 2, max = 30, message = "Color must be between 2 and 30 characters")
    private String color;

    @NotBlank(message = "License plate number is required")
    @Size(min = 2, max = 15, message = "License plate must be between 2 and 15 characters")
    @Pattern(regexp = "^[A-Za-z0-9\\-\\s]+$", message = "License plate can only contain letters, numbers, hyphens, and spaces")
    private String licensePlateNumber;

    @NotBlank(message = "Body type is required")
    @Pattern(regexp = "^(Sedan|SUV|Hatchback|Pickup|Coupe|Convertible)$",
            message = "Please select a valid body type")
    private String bodyType;

    @NotBlank(message = "Engine type is required")
    @Pattern(regexp = "^(Gasoline|Diesel|Electric|Hybrid)$",
            message = "Please select a valid engine type")
    private String engineType;

    @NotBlank(message = "Transmission is required")
    @Pattern(regexp = "^(Automatic|Manual)$",
            message = "Please select a valid transmission type")
    private String transmission;

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getLicensePlateNumber() { return licensePlateNumber; }
    public void setLicensePlateNumber(String licensePlateNumber) { this.licensePlateNumber = licensePlateNumber; }

    public String getBodyType() { return bodyType; }
    public void setBodyType(String bodyType) { this.bodyType = bodyType; }

    public String getEngineType() { return engineType; }
    public void setEngineType(String engineType) { this.engineType = engineType; }

    public String getTransmission() { return transmission; }
    public void setTransmission(String transmission) { this.transmission = transmission; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
