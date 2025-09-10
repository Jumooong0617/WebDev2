package com.yole.carapp.controllers;

import com.yole.carapp.dto.CarDTO;
import com.yole.carapp.models.Car;
import com.yole.carapp.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    // ✅ Show list of cars with optional search
    @GetMapping
    public String index(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Car> cars;

        if (search != null && !search.trim().isEmpty()) {
            cars = carService.searchCars(search); // filtered list
        } else {
            cars = carService.getAllCars(); // full list
        }

        model.addAttribute("cars", cars);
        model.addAttribute("search", search); // keep search value in input
        return "index";
    }

    // ✅ Show add form
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("carDTO", new CarDTO());
        return "new";
    }

    // ✅ Save new car
    @PostMapping("/save")
    public String saveCar(@Valid @ModelAttribute("carDTO") CarDTO carDTO,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        }
        carService.save(carDTO);
        return "redirect:/";
    }

    // ✅ Delete car
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return "redirect:/";
    }

    // ✅ Show edit form (pre-filled)
    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);

        CarDTO carDTO = new CarDTO();
        carDTO.setLicensePlateNumber(car.getLicensePlateNumber());
        carDTO.setMake(car.getMake());
        carDTO.setModel(car.getModel());
        carDTO.setYear(car.getYear());
        carDTO.setColor(car.getColor());
        carDTO.setBodyType(car.getBodyType());
        carDTO.setEngineType(car.getEngineType());
        carDTO.setTransmission(car.getTransmission());

        model.addAttribute("carDTO", carDTO);
        model.addAttribute("carId", id);

        return "edit";
    }

    // ✅ Update car
    @PostMapping("/update/{id}")
    public String storeUpdateCar(@PathVariable Long id,
                                 @Valid @ModelAttribute("carDTO") CarDTO carDTO,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("carId", id);
            return "edit";
        }
        carService.update(id, carDTO);
        return "redirect:/";
    }

    // ✅ Edit with empty fields (reset button)
    @GetMapping("/edit-empty")
    public String editEmptyCar(@RequestParam Long id, Model model) {
        CarDTO carDTO = new CarDTO(); // keep empty
        model.addAttribute("carDTO", carDTO);
        model.addAttribute("carId", id); // preserve ID
        return "edit";
    }
}
