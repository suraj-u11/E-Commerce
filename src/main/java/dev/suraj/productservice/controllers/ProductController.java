package dev.suraj.productservice.controllers;

import dev.suraj.productservice.dtos.FakeStoreCreateOrUpdateProduct;
import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private IProductService productService;
    public ProductController(@Qualifier("FakeStoreProductService") IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody FakeStoreCreateOrUpdateProduct newProduct){
        Product createdProduct = productService.createProduct(newProduct.getTitle(), newProduct.getPrice(), newProduct.getDescription(), newProduct.getImage(), newProduct.getCategory());

        if(createdProduct == null)
            return  new ResponseEntity<>(HttpStatusCode.valueOf(400));
        else
            return new ResponseEntity<Product>(createdProduct, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody FakeStoreCreateOrUpdateProduct updateProduct){
        Product updatedProduct = productService.updateProductById(id, updateProduct.getTitle(), updateProduct.getPrice(), updateProduct.getDescription(), updateProduct.getImage(), updateProduct.getCategory());

        if(updatedProduct == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        else
            return new ResponseEntity<>(updatedProduct,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if(products.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));

        return new ResponseEntity<List<Product>>(products, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);

        if(product == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        else
            return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id){
        Product deletedProduct = productService.deleteProductById(id);
        if(deletedProduct == null)
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        else
            return new ResponseEntity<>(deletedProduct, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/products/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = productService.getAllCategories();

        if(categories.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));

        return new ResponseEntity<>(categories, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        List<Product> productsByCategory = productService.getProductsByCategory(categoryName);

        if(productsByCategory.isEmpty())
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        else
            return new ResponseEntity<>(productsByCategory,HttpStatusCode.valueOf(200));
    }
}