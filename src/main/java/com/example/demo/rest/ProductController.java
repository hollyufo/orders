package com.example.demo.rest;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor

public class ProductController {

    private final ProductService productService;

    // get all products
    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
    @PostMapping("/") @ResponseBody
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }
    // find product by id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }
    // delete product by id
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
