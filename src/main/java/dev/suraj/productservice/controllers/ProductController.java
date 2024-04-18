package dev.suraj.productservice.controllers;

import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.services.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public void createProduct(){

    }

    @PutMapping("/products/{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable("id") Long id){
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/products/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }
}
