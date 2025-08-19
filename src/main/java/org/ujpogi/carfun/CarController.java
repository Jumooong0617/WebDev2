package org.ujpogi.carfun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        return "car_form";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/";
    }
}
