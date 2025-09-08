package com.yole.carapp.controllers;

import com.yole.carapp.dto.CarDTO;
import com.yole.carapp.models.Car;
import com.yole.carapp.repositories.CarRepository;
import com.yole.carapp.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final CarRepository carRepository;

    public HomeController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // ✅ Home page with search
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model) {
        List<Car> cars;
        if (search.isEmpty()) {
            cars = carRepository.findAll();
        } else {
            cars = carRepository
                    .findByMakeContainingIgnoreCaseOrLicensePlateNumberContainingIgnoreCaseOrColorContainingIgnoreCaseOrBodyTypeContainingIgnoreCaseOrEngineTypeContainingIgnoreCaseOrTransmissionContainingIgnoreCase(
                            search, search, search, search, search, search
                    );
        }
        model.addAttribute("cars", cars);
        model.addAttribute("search", search); // ✅ keep input value after searching
        return "index";
    }

    // ✅ Delete car
    @GetMapping("/delete")
    public String deleteCar(@RequestParam int id) {
        carRepository.deleteById(id);
        return "redirect:/";
    }

    // ✅ Add car page
    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("activeMenu", "new");
        model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
        model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
        return "new";
    }

    // ✅ Save new car
    @PostMapping("/save")
    public String save(@ModelAttribute("car") @Valid CarDTO carDTO,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "add_car"; // back to form if validation fails
        }

        // Map DTO to entity
        Car newCar = new Car();
        newCar.setMake(carDTO.getMake());
        newCar.setModel(carDTO.getModel());
        newCar.setYear(carDTO.getYear());
        newCar.setLicensePlateNumber(carDTO.getLicensePlateNumber()); // Added
        newCar.setColor(carDTO.getColor());
        newCar.setBodyType(carDTO.getBodyType());
        newCar.setEngineType(carDTO.getEngineType());
        newCar.setTransmission(carDTO.getTransmission());

        carRepository.save(newCar);
        return "redirect:/"; // success, redirect to list
    }

    // ✅ Edit car page
    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setMake(car.getMake());
            carDTO.setModel(car.getModel());
            carDTO.setYear(car.getYear());
            carDTO.setLicensePlateNumber(car.getLicensePlateNumber()); // Added
            carDTO.setColor(car.getColor());
            carDTO.setBodyType(car.getBodyType());
            carDTO.setEngineType(car.getEngineType());
            carDTO.setTransmission(car.getTransmission());

            model.addAttribute("car", carDTO);
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            model.addAttribute("carId", id); // keep ID for updating
            return "edit_car"; // edit_car.html
        }
        return "redirect:/"; // not found, back to list
    }

    // ✅ Update car
    @PostMapping("/update")
    public String update(@RequestParam int id,
                         @ModelAttribute("car") @Valid CarDTO carDTO,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            model.addAttribute("carId", id);
            return "edit_car"; // back to form if errors
        }

        Car existingCar = carRepository.findById(id).orElse(null);
        if (existingCar == null) {
            return "redirect:/"; // no car found, back to list
        }

        // Update fields
        existingCar.setMake(carDTO.getMake());
        existingCar.setModel(carDTO.getModel());
        existingCar.setYear(carDTO.getYear());
        existingCar.setLicensePlateNumber(carDTO.getLicensePlateNumber()); // Added
        existingCar.setColor(carDTO.getColor());
        existingCar.setBodyType(carDTO.getBodyType());
        existingCar.setEngineType(carDTO.getEngineType());
        existingCar.setTransmission(carDTO.getTransmission());

        carRepository.save(existingCar);
        return "redirect:/"; // success
    }

    // ✅ View details
    @GetMapping("/view")
    public String view(@RequestParam int id, Model model) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));

        model.addAttribute("car", car);
        return "view";
    }
}
