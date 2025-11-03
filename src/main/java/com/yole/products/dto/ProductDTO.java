package com.yole.products.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @Min(value = 1, message = "Stock must be at least 1")
        int stock,

        @NotBlank(message = "Unit is required")
        String unit,

        @DecimalMin(value = "1.0", message = "Price must be at least 1.0")
        BigDecimal price
) {}

