package dev.suraj.productservice;

import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.repositories.CategoryRepo;
import dev.suraj.productservice.repositories.ProductRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindAllProducts() {
        List<Product> products = productRepo.findProductByIsDeletedFalse();
        System.out.println(products.stream().toList());
    }

    @Test
    public void testFindCategoryByTitle() {
        Category category = categoryRepo.findByCategoryName("clothing");
        System.out.println(category.getCategoryName());
    }

    @Test
    public void testFindProductById() {
        Product product = productRepo.findProductByIdIsAndIsDeletedFalse(52L);
        System.out.println(product.getId() + " " + product.getCategory().getCategoryName());
    }

    @Test
    @Transactional
    public void testCategoryBasedProducts() {
        Category category = categoryRepo.findByCategoryName("clothing");
        System.out.println(category.getCategoryName());
        List<Product> products = category.getProducts();
        System.out.println(products);
    }

}
