package com.yole.carapp;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    CarRepository carRepository;

    public HomeController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model) {
        List<Car> cars = carRepository.findAll(); // ✅ only keep this
        model.addAttribute("cars", carRepository.findAll());
//        model.addAttribute("activeMenu", "home");
        cars.forEach(car -> {
            System.out.println(car.getMake());
        });
        return "index";
    }

    @GetMapping("/delete")
    public String deleteCar(@RequestParam int id) {
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String add(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        model.addAttribute("activeMenu", "new");

        model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
        model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
        return "new";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "new";
        }

        carRepository.save(car); // ✅ let service assign ID
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Car c = carRepository.findById(id).get();
        if (c != null) {
            model.addAttribute("car", c);
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "edit";
        }
        return "redirect:/";
    }

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

//    @GetMapping("/car/{id}")
//    public String view(@PathVariable int id, Model model) {
//        Car c = carRepository.findById(id).get();
//        model.addAttribute("car", c);
//        return "view"; // create a simple view.html
//    }
}