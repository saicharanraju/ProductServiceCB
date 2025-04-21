package com.scaler.productservicecb.controllers;

import com.scaler.productservicecb.dto.ErrorResponseDTO;
import com.scaler.productservicecb.dto.FakeStoreRequestDTO;
import com.scaler.productservicecb.dto.ListProductsResponseDTO;
import com.scaler.productservicecb.dto.ProductResponseDTO;
import com.scaler.productservicecb.exceptions.DBNotFoundException;
import com.scaler.productservicecb.exceptions.DBTimeoutException;
import com.scaler.productservicecb.exceptions.ProductNotFoundException;
import com.scaler.productservicecb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scaler.productservicecb.models.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("RealDBProductService") //Spring will create bean RealDBProductService, as we mentioned @Qualifier on RealProductService class and pass it here.
    ProductService productService; //Controller needs to have ProductService obj. ready which we can use and call the Service for actual logic.
                                   //We are using Interface(ProductService not FakeStoreProductResponse) to guarantee that we have all methods implemented.
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable("id") String productId) throws DBTimeoutException, DBNotFoundException, ProductNotFoundException {
            Product product = productService.getSingleProduct(productId);
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(product);
            productResponseDTO.setResponseMessage("Successfully retrieved single product");
            ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
            return responseEntity;
    }

    @GetMapping("/products")
    public ResponseEntity<ListProductsResponseDTO> getAllProducts(){
            List<Product> products = productService.getAllProducts();
            ListProductsResponseDTO responseDTO = new ListProductsResponseDTO();
            responseDTO.setProductList(products);
            responseDTO.setResponseMessage("Successfully retrieved all products");
            ResponseEntity<ListProductsResponseDTO> responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.OK);
            return responseEntity;

    }

    @GetMapping("/search")
    //url: GET   http://localhost:8080/search?catName=laptop
    public List<Product>  getProductsByCategoryName(@RequestParam("catName") String categoryName){
        List<Product> products = productService.getProductsByCategoryName(categoryName);
        return products;

    }

//    @GetMapping("/search")
//    // http:/localhost:8080/products?text="Hello"
//    public List<Product> searchProducts(@RequestParam("text") String queryText){
//        List<Product> products = productService.searchProducts(queryText);
//        return products;
//    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        Product savedProduct = productService.createProduct(product);
        return savedProduct;
    }
    //To check where the exception goes if it happens in service. service or controller ?
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException errorObject){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorMessage("from controller "+errorObject.getMessage());
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
