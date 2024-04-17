package dev.suraj.productservice.controllers;

import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.services.IProductService;
import org.springframework.web.bind.annotation.*;

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
    public void getAllProducts() {

    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable("id") Long id){

    }

    @GetMapping("/products/categories")
    public void getAllCategories() {

    }

    @GetMapping("/products/category/{categoryName}")
    public void getProductByCategory(@PathVariable("categoryName") String categoryName) {

    }
}
