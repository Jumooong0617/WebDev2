package com.yole.carapp.controller;


import com.yole.carapp.dto.EmployeeDTO;
import com.yole.carapp.entity.Employee;
import com.yole.carapp.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) String keyword, Model model) {
        List<Employee> employees;
        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
        } else {
            employees = employeeRepository.findAll();
        }
        model.addAttribute("employees", employees);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") @Valid EmployeeDTO employeeDTO,
                       BindingResult bindingResult,
                       Model model) {

        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "add";
        }

        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getName());
        newEmployee.setEmail(employeeDTO.getEmail());

        employeeRepository.save(newEmployee);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setEmail(employee.getEmail());

            model.addAttribute("employee", employeeDTO);
            model.addAttribute("employeeId", id);
            return "edit";
        }

        model.addAttribute("message", "This ID isn't available. Sorry about that.");
        return "error/error";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
                         BindingResult bindingResult,
                         Model model) {

        Long id = employeeDTO.getId();

        if (employeeRepository.existsByEmail(employeeDTO.getEmail()) &&
                !employeeRepository.findById(id).get().getEmail().equals(employeeDTO.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeId", id);
            return "edit";
        }

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            return "redirect:/";
        }

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setEmail(employeeDTO.getEmail());

        employeeRepository.save(existingEmployee);
        return "redirect:/";
    }

}