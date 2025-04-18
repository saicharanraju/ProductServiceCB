package com.scaler.productservicecb.services;

import com.scaler.productservicecb.dto.FakeStoreResponse;
import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements ProductService{

    // It has to hit the APIs of Fakestoreserver; basically, this class is going to be a client of another server.
    RestTemplate restTemplate = new RestTemplate();
    // we use RestTemplate to call 3rd party APIs.

    @Override
    public Product getSingleProduct(String productId) {
        FakeStoreResponse response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponse.class
        );
        //converting the third-party response to my models and then work on them.
        Product product = new Product();
        product.setId(response.getId());
        product.setName(response.getTitle());
        product.setPrice(response.getPrice() * 1.0);
        product.setDescription(response.getDescription());
        product.setImageUrl(response.getImage());
        // In FakeStoreResponse we are reading Category as String, in Product it is an Object. So, we will first create Category
        // object and store the string in it. Before passing and pass Category obj. to product.
        Category category = new Category();
        category.setName(response.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponse[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreResponse[].class
        );
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreResponse response : responseArray){
            Product product = new Product();
            product.setId(response.getId());
            product.setName(response.getTitle());
            product.setPrice(response.getPrice() * 1.0);
            product.setDescription(response.getDescription());
            product.setImageUrl(response.getImage());
            Category category = new Category();
            category.setName(response.getCategory());
            product.setCategory(category);

            productsList.add(product);
        }
        return productsList;
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
