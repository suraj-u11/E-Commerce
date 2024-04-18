package dev.suraj.productservice.dtos;

import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

    public Product toProductObj() {
        Product product = new Product();
        Category category = new Category();

        product.setId(getId());
        product.setTitle(getTitle());
        product.setPrice(getPrice());
        product.setDescription(getDescription());
        product.setImageUrl(getImage());
        category.setCategoryName(getCategory());
        product.setCategory(category);

        return product;
    }
}
