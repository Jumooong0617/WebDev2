package com.yole.carapp;

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
        Car car = new Car();
        model.addAttribute("car", car);
        model.addAttribute("activeMenu", "new");

        model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
        model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
        return "new";
    }

    // ✅ Save new car
    @PostMapping("/save")
    public String save(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "new";
        }
        carRepository.save(car);
        return "redirect:/";
    }

    // ✅ Edit car page
    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Car c = carRepository.findById(id).orElse(null);
        if (c != null) {
            model.addAttribute("car", c);
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "edit";
        }
        return "redirect:/";
    }

    // ✅ Update car
    @PostMapping("/update")
    public String update(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "edit";
        }
        carRepository.save(car);
        return "redirect:/";
    }
}
