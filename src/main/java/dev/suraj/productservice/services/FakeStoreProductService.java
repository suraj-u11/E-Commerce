package dev.suraj.productservice.services;

import dev.suraj.productservice.dtos.FakeStoreProductDto;
import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProducts = response.getBody();

        List<Product> products = new ArrayList<>();

        if(fakeStoreProducts == null)
            return null;

        for(FakeStoreProductDto fakeStoreProductDtoObj: fakeStoreProducts){
            Product product = new Product();
            Category categoryName = new Category();

            product.setId(fakeStoreProductDtoObj.getId());
            product.setTitle(fakeStoreProductDtoObj.getTitle());
            product.setDescription(fakeStoreProductDtoObj.getDescription());
            product.setPrice(fakeStoreProductDtoObj.getPrice());
            product.setImageUrl(fakeStoreProductDtoObj.getImage());
            categoryName.setCategoryName(fakeStoreProductDtoObj.getCategory());
            product.setCategory(categoryName);

            products.add(product);
        }

        return products;
    }
    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setCategoryName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public List<Category> getAllCategories() {
        ResponseEntity<String[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);

        String[] fakeStoreCategories = response.getBody();
        List<Category> categories = new ArrayList<>();

        for(int i = 0; i < fakeStoreCategories.length; i++) {
            Category category = new Category();
            category.setId((long)i);
            category.setCategoryName(fakeStoreCategories[i]);
            categories.add(category);
        }

        return categories;
    }
}
