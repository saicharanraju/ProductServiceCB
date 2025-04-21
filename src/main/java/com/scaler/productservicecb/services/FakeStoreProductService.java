package com.scaler.productservicecb.services;

import com.scaler.productservicecb.dto.ErrorResponseDTO;
import com.scaler.productservicecb.dto.FakeStorePOSTResponseDTO;
import com.scaler.productservicecb.dto.FakeStoreRequestDTO;
import com.scaler.productservicecb.dto.FakeStoreResponseDTO;
import com.scaler.productservicecb.exceptions.DBNotFoundException;
import com.scaler.productservicecb.exceptions.DBTimeoutException;
import com.scaler.productservicecb.exceptions.ProductNotFoundException;
import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    // It has to hit the APIs of Fakestoreserver; basically, this class is going to be a client of another server.
    @Autowired
    RestTemplate restTemplate;
    // we use RestTemplate to call 3rd party APIs.

    @Override
    public Product getSingleProduct(String productId) throws ProductNotFoundException, DBNotFoundException, DBTimeoutException {
        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponseDTO.class
        );
        if(response == null){
            throw new ProductNotFoundException("product with id " + productId + " not found");
        }
        ConnectToDB();      // This will stop the execution when given correct input and return exception.
        executeSQLQuery();  // This will stop the execution when given correct input and return exception.
        //converting the third-party response to my models and then work on them.
        Product product = response.toProduct();
        return product;
    }

    private void ConnectToDB() throws DBNotFoundException {
        throw new DBNotFoundException("db not found ");
    }
    private void executeSQLQuery() throws DBTimeoutException{
        throw new DBTimeoutException("db timeout trying to execute query ");
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponseDTO[] responseArray = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreResponseDTO[].class
        );
        List<Product> productsList = new ArrayList<>();
        for(FakeStoreResponseDTO response : responseArray){
            Product product = response.toProduct();
            productsList.add(product);
        }
        return productsList;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return List.of();
    }


    @Override
    public List<Product> searchProducts(String searchText) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) { //fakeStoreRequestDTO is our input from postman.
        FakeStorePOSTResponseDTO savedProductResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                 fakeStoreRequestDTO,
                FakeStorePOSTResponseDTO.class);
        Product product = savedProductResponse.toProduct();
        return product;
    }
    //Now, the ProductService have actual methods. It doesnot contain 'convert this to that'.
    //And, it's the object responsibility to convert itself.(FakeStoreResponseDTO obj. FakeStorePOSTResponseDTO obj.)

}
