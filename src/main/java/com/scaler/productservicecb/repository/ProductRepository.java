package com.scaler.productservicecb.repository;

import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>  {
    List<Product> getAllProducts();
    Product getProductById(String productId);
    List<Product> getAllByPriceGreaterThan(Double value);
    // select * from products where price > value
    List<Product> getAllByNameLike(String text);
    // select * from product where Name like '%text%'
    List<Product> getAllByNameLikeAndPriceLessThanAndCategory(String text, Double priceValue, Category category);

}
