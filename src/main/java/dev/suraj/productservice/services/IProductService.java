package dev.suraj.productservice.services;

import dev.suraj.productservice.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
}
