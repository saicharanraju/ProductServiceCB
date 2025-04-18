package com.scaler.productservicecb.controllers;

import com.scaler.productservicecb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.scaler.productservicecb.models.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService; //Controller needs to have ProductService obj. ready which we can use and call the Service for actual logic.
                                   //We are using Interface(ProductService not FakeStoreProductResponse) to guarantee that we have all methods implemented.
    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") String productId){
        Product product = productService.getSingleProduct(productId);
        return product;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/search")
    // http:/localhost:8080/products?text="Hello"
    public List<Product> searchProducts(@RequestParam("text") String queryText){
        List<Product> products = productService.searchProducts(queryText);
        return products;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product productReceivedFromRequest){
        Product savedProduct = productService.createProduct(productReceivedFromRequest);
        return savedProduct;
    }

}
