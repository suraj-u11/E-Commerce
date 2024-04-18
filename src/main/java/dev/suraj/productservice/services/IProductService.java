package dev.suraj.productservice.services;

import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;

import java.util.List;

public interface IProductService {
    public Product createProduct(String title, double price, String description, String image, String category);
    public Product updateProductById(Long id, String title, double price, String description, String image, String category);
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public List<Category> getAllCategories();
    public List<Product> getProductsByCategory(String categoryName);
    public Product deleteProductById(Long id);
}
