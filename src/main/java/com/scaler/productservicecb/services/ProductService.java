package com.scaler.productservicecb.services;

import com.scaler.productservicecb.dto.FakeStoreRequestDTO;
import com.scaler.productservicecb.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(String productId);

    List<Product> getAllProducts();

    List<Product> searchProducts(String searchText);

    //create object in your own DB.
    Product createProduct(Product product);

    //create object in FakeStore DB.
    Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO);
}
