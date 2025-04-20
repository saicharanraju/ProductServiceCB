package com.scaler.productservicecb.services;

import com.scaler.productservicecb.dto.FakeStoreRequestDTO;
import com.scaler.productservicecb.models.Product;
import com.scaler.productservicecb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("RProductService")
public class RealProductService implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public Product getSingleProduct(String productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        List<Product> productsFromDB = productRepository.getAllProducts();

        //core logic, filter products, whether their name contains searchText or not
        List<Product> result = new ArrayList<>();
        for(Product product: productsFromDB){
            if(product.getName().contains(searchText)){
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        return null;
    }
}
