package org.example.springbootmysql.service;


import lombok.RequiredArgsConstructor;
import org.example.springbootmysql.model.Product;
import org.example.springbootmysql.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct (Long id, Product newProduct) {
        Product product = getProductById(id);
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        return productRepository.save(product);
    }
}
