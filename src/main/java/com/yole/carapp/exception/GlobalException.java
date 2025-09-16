package com.yole.carapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({ResourceNotFoundException.class})
    public String handleResourceNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/error";  // Thymeleaf template: templates/error/error.html
    }

    @ExceptionHandler({Exception.class})
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("message", "Unexpected error: " + ex.getMessage());
        return "error/error";
    }
}
