package dev.suraj.productservice.repositories;

import dev.suraj.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save(Product product);
    List<Product> findProductByIsDeletedFalse();
    Product findProductByIdIsAndIsDeletedFalse(Long id);
    List<Product> findProductByIsDeletedFalseAndCategory_CategoryNameAndCategory_IsDeletedFalse(String category);

//    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE is_deleted = false")
//    List<Product> findProductByIsDeletedFalse();
//    @Query("select p from Product p where p.id = :id")
//    Product findProductByIdIsAndIsDeletedFalse(@Param("id") Long id);
//    List<Product> findProductByIsDeletedFalseAndCategory_CategoryNameAndCategory_IsDeletedFalse(String category);
}
