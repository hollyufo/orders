package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }
    // find product by uuid

    // find product by id
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
    // delete product by id
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
