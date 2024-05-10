package dev.suraj.productservice.services;

import dev.suraj.productservice.dtos.FakeStoreProductDto;
import dev.suraj.productservice.models.Category;
import dev.suraj.productservice.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements IProductService{
    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product createProduct(String title, double price, String description, String image, String category) {

        if(title.isBlank() || price == 0.0 || image.isBlank() || category.isBlank())
            return null;

        FakeStoreProductDto newFakeStoreProductDto = new FakeStoreProductDto();
        newFakeStoreProductDto.setTitle(title);
        newFakeStoreProductDto.setPrice(price);
        newFakeStoreProductDto.setDescription(description);
        newFakeStoreProductDto.setImage(image);
        newFakeStoreProductDto.setCategory(category);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products", newFakeStoreProductDto, FakeStoreProductDto.class);

        return response.toProductObj();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDtoObj: response){
            products.add(fakeStoreProductDtoObj.toProductObj());
        }

        return products;
    }
    @Override
    public Product getProductById(Long id) {
        Product productFromRedisCache= (Product) redisTemplate.opsForValue().get(id.toString());
        if(productFromRedisCache != null)
            return productFromRedisCache;

        FakeStoreProductDto response = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);

        if(response == null)
            return null;

        Product product = response.toProductObj();
        redisTemplate.opsForValue().set(id.toString(), product);

        return product;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);

        List<Category> categories = new ArrayList<>();

        for(int i = 0; i < response.length; i++) {
            Category category = new Category();
            category.setId((long)i);
            category.setCategoryName(response[i]);
            categories.add(category);
        }

            return categories;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        FakeStoreProductDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+categoryName, FakeStoreProductDto[].class);

        if(response == null)
            return null;

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDtoObj: response) {
            products.add(fakeStoreProductDtoObj.toProductObj());
        }

        return products;
    }

    @Override
    public Product updateProductById(Long id, String title, double price, String description, String image, String category) {

        if(title.isBlank() && price == 0.0 && image.isBlank() && category.isBlank() && description.isBlank())
            return null;

        FakeStoreProductDto updateFakeStoreProductDto = new FakeStoreProductDto();
        updateFakeStoreProductDto.setTitle(title);
        updateFakeStoreProductDto.setPrice(price);
        updateFakeStoreProductDto.setDescription(description);
        updateFakeStoreProductDto.setImage(image);
        updateFakeStoreProductDto.setCategory(category);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, new HttpEntity<>(updateFakeStoreProductDto), FakeStoreProductDto.class);

        return response.getBody().toProductObj();
    }

    @Override
    public Product deleteProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/"+id, HttpMethod.DELETE, null, FakeStoreProductDto.class);

        return response.getBody().toProductObj();
    }
}
