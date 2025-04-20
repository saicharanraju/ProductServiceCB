package com.scaler.productservicecb.repository;

import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    public Product save(Product product){
        //connect to DB.
        //execute the query.

        return null;
    }

    public List<Product> getAllProducts(){
        //connect to DB
        //execute the query

        return new ArrayList<Product>();
    }

}
