package com.scaler.productservicecb.services;

import com.scaler.productservicecb.dto.FakeStoreRequestDTO;
import com.scaler.productservicecb.exceptions.DBNotFoundException;
import com.scaler.productservicecb.exceptions.DBTimeoutException;
import com.scaler.productservicecb.exceptions.ProductNotFoundException;
import com.scaler.productservicecb.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(String productId) throws ProductNotFoundException, DBTimeoutException, DBNotFoundException;

    List<Product> getAllProducts();

    List<Product> getProductsByCategoryName(String categoryName);

    List<Product> searchProducts(String searchText);

    //create object in your own DB.
    Product createProduct(Product product);

    //create object in FakeStore DB.
    Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO);
}
