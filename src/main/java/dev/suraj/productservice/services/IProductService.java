package dev.suraj.productservice.services;

import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public List<Category> getAllCategories();
    public List<Product> getProductsByCategory(String categoryName);
}
