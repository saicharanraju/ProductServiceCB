package com.scaler.productservicecb.services;

import com.scaler.productservicecb.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(String productId);

    List<Product> getAllProducts();

    List<Product> searchProducts(String searchText);

    Product createProduct(Product product);
}
