package com.scaler.productservicecb.controllers;

import com.scaler.productservicecb.dto.FakeStoreRequestDTO;
import com.scaler.productservicecb.dto.ProductResponseDTO;
import com.scaler.productservicecb.exceptions.DBNotFoundException;
import com.scaler.productservicecb.exceptions.DBTimeoutException;
import com.scaler.productservicecb.exceptions.ProductNotFoundException;
import com.scaler.productservicecb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable("id") String productId){
        try {
            Product product = productService.getSingleProduct(productId);
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(product);
            productResponseDTO.setResponseMessage("Successfully retrieved single product");
            ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch (ProductNotFoundException pnfe){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(null);
            productResponseDTO.setResponseMessage(pnfe.getMessage() + " exception type 1");
            ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
//        catch (DBNotFoundException dbnfe){
//            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//            productResponseDTO.setProduct(null);
//            productResponseDTO.setResponseMessage(dbnfe.getMessage() + "exception type 2");
//            return productResponseDTO;
//        }
//        catch (DBTimeoutException dbte){
//            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//            productResponseDTO.setProduct(null);
//            productResponseDTO.setResponseMessage(dbte.getMessage() + "exception type 3");
//            return productResponseDTO;
//        }
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
    public Product createProduct(@RequestBody FakeStoreRequestDTO fakeStoreRequestDTO){ // we'll get request in format of FakeStoreRequestDTO
        Product savedProduct = productService.createProduct(fakeStoreRequestDTO);
        return savedProduct;
    }

}
