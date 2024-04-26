package dev.suraj.productservice.repositories;

import dev.suraj.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);
    List<Product> findProductByIsDeletedFalse();
    Product findProductByIdIsAndIsDeletedFalse(Long id);
    List<Product> findProductByIsDeletedFalseAndCategory_CategoryNameAndCategory_IsDeletedFalse(String category);
}
