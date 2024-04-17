package dev.suraj.productservice.services;

import dev.suraj.productservice.models.Product;

public interface IProductService {
    public Product getProductById(Long id);
}
