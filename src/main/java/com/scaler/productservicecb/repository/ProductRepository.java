package com.scaler.productservicecb.repository;

import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import com.scaler.productservicecb.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product product);

    List<Product> getProductsByCategoryIn(List<Category> categories);
    Integer countProductByPriceLessThan(Double value);
    List<Product> getProductByName(String name);
    List<Product> getProductByNameLikeAndDescriptionLikeOrderByPriceDesc(String nameText, String descText);
    List<Product> getProductByCategoryName(String categoryName); //behind ORM library will create join between Product and Category
    void deleteProductById(Long id); //permanent deletion of the row
    //HQL
    @Query("select p from Product p")
    List<Product> getAllProducts();
    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> allProductsGivenCatNameUsingHQL(@Param("categoryName") String catName);
    @Query(value = "select p.* from Product p LEFT JOIN Category c ON p.caterogy_id = c.id where c.name= :categoryName", nativeQuery = true)
    List<Product> properSQLQuery(@Param("categoryName") String catName);
    //in HQL, always give alia for Projection
    @Query(value = "select p.id, p.name from Product p LEFT JOIN Category c ON p.category_id = c.id where c.name= :categoryName", nativeQuery = true)
    List<ProductProjection> fetchUsingProjection(@Param("categoryName") String catName);

    @Query(value = "select p.id as id, p.name as name from Product p where p.category.name = :categoryName")
    List<ProductProjection> fetchUsingHQLName(@Param("categoryName") String catName);



}
