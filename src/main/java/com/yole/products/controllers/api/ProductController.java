package com.yole.products.controllers.api;


import com.yole.products.dto.ProductDTO;
import com.yole.products.models.Product;
import com.yole.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<Product> getAll() {
        return service.findAll();
    }

    @PostMapping("/products")
    public Product create(@Valid @RequestBody ProductDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/products/{id}")
    public Product update(@PathVariable int id, @Valid @RequestBody ProductDTO dto) {
        Product existing = service.findById(id);
        if (existing == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        return service.update(existing, dto);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable int id) {
        if (service.findById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        service.delete(id);
    }
}
