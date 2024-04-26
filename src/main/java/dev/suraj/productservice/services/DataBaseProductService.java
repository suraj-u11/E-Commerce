package dev.suraj.productservice.services;

import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import dev.suraj.productservice.repositories.CategoryRepo;
import dev.suraj.productservice.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DataBaseProductService")
public class DataBaseProductService implements IProductService{
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public DataBaseProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product createProduct(String title, double price, String description, String image, String category) {
        Product newProduct = new Product();
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setDescription(description);
        newProduct.setImageUrl(image);

        Category categoryFromDatabase = categoryRepo.findByCategoryName(category);
        if(categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setCategoryName(category);
            categoryFromDatabase = newCategory;
        }

        newProduct.setCategory(categoryFromDatabase);
        return productRepo.save(newProduct);
    }

    @Override
    public Product updateProductById(Long id, String title, double price, String description, String image, String category) {
        Product updatedProduct = productRepo.findProductByIdIsAndIsDeletedFalse(id);

        if(updatedProduct == null || !categoryRepo.existsCategoriesByCategoryName(category))
            return null;

        updatedProduct.setId(id);
        updatedProduct.setTitle(title);
        updatedProduct.setPrice(price);
        updatedProduct.setDescription(description);
        updatedProduct.setImageUrl(image);

        Category categoryFromDatabase = categoryRepo.findByCategoryName(category);
        updatedProduct.setCategory(categoryFromDatabase);

        return productRepo.save(updatedProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findProductByIsDeletedFalse();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findProductByIdIsAndIsDeletedFalse(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findCategoryByIsDeletedFalse();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepo.findProductByIsDeletedFalseAndCategory_CategoryNameAndCategory_IsDeletedFalse(categoryName);
    }

    @Override
    public Product deleteProductById(Long id) {
        Product deleteProduct = productRepo.findProductByIdIsAndIsDeletedFalse(id);

        if(deleteProduct == null)
            return null;

        deleteProduct.setIsDeleted(true);
        return productRepo.save(deleteProduct);
    }
}
