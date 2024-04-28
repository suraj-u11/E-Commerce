package dev.suraj.productservice.repositories;

import dev.suraj.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findByCategoryName(String title);
    Boolean existsCategoriesByCategoryName(String title);
    List<Category> findCategoryByIsDeletedFalse();
//    @Query(nativeQuery = true, value = "SELECT * FROM category WHERE category_name = :name")
//    Category findByCategoryName(@Param("name") String title);
//    Boolean existsCategoriesByCategoryName(String title);
//    @Query(nativeQuery = true, value = "SELECT * FROM category WHERE is_deleted = false")
//    List<Category> findCategoryByIsDeletedFalse();
}