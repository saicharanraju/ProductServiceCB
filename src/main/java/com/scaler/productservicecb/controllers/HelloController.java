package com.scaler.productservicecb.controllers;

import com.scaler.productservicecb.exceptions.ProductNotFoundException;
import com.scaler.productservicecb.models.Product;
import com.scaler.productservicecb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    @Qualifier("RealDBProductService")
    ProductService productService;
    @GetMapping("/hello")
    public String sayHello() throws ProductNotFoundException {

        throw new ProductNotFoundException("hello controller");
//        List<Product> productList = productService.getAllProducts();
//        return "One day at a time "+productList.size()+" products";
    }

}
