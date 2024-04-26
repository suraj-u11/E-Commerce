package dev.suraj.productservice.repositories;

import dev.suraj.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findByCategoryName(String title);
    Boolean existsCategoriesByCategoryName(String title);
    List<Category> findCategoryByIsDeletedFalse();
}