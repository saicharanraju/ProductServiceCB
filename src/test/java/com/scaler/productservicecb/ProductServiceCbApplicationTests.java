package com.scaler.productservicecb;

import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import com.scaler.productservicecb.projections.ProductProjection;
import com.scaler.productservicecb.repository.CategoryRepository;
import com.scaler.productservicecb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceCbApplicationTests {

    @Test
    void contextLoads() {
    }

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceCbApplicationTests(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Test
    void testAllProductsUsingHQL(){ //this will map to the ProductRepository and execute the query present in this method(getAllProducts).
        List<Product> products = productRepository.getAllProducts();
        for(Product product : products){
            System.out.println(product.getName());
        }
    }

    @Test
    void testAllProductsUsingHQLAndCatName(){
        List<Product> productList = productRepository.allProductsGivenCatNameUsingHQL("laptop");
        for(Product product : productList){
            System.out.println(product.getName());
        }
    }

    @Test
    void sqlChalalo(){
        List<Product> products = productRepository.properSQLQuery("laptop");
        for(Product product : products){
            System.out.println(product.getName());
        }
    }

    @Test
    void sqlFetchUsingProjection(){
        List<ProductProjection> productList = productRepository.fetchUsingProjection("laptop");
        for(ProductProjection projection: productList){
            System.out.println(projection.getId() + " " + projection.getName());
        }
    }

    @Test
    void hqlFetchUsingProjection(){
        List<ProductProjection> productList = productRepository.fetchUsingHQLName("laptop");
        for(ProductProjection projection: productList){
            System.out.println(projection.getId() + " " + projection.getName());
        }
    }

    @Test
    void fetchCategoryAndListOfProducts(){
        Optional<Category> categoryOptional = categoryRepository.getCategoryByName("laptop");
        if(categoryOptional.isEmpty()){
            return;
        }
        Category category = categoryOptional.get();
        System.out.println(category.getName());
    }

        @Test
    void fetchProduct(){
        Optional<Product> productOptional = productRepository.findById(1L);
        if(productOptional.isEmpty()){
            return;
        }
        Product product = productOptional.get();
        System.out.println(product.getName());
    }

        @Test
        void fetchProductsLazilyP2() {
            Optional<Category> categoryOptional = categoryRepository.getCategoryByName("laptop");
            if (categoryOptional.isEmpty()) {
                return;
            }
            Category category = categoryOptional.get();
            System.out.println(category.getName());

            List<Product> products = category.getProducts();
            System.out.println(products.size());
            for (Product product : products) {
                System.out.println(product.getName());
            }
        }
        @Test
        void testNPlusOneProblem(){
        // 1. for N categories we made N+1 database calls(API calls)
        // 2. first fetches all the categories when below line is executed(make 1 db call and fetch N categories)
        // 3. for each category we make another db call. overall N+1 calls
        List<Category> categories = categoryRepository.getCategoriesByNameContaining("laptop");
        // you make 1 single DB call -> N Categories
        for(Category category: categories) {
            System.out.println(category.getName());

            List<Product> products = category.getProducts();
            // for each Category -> You make another DB call
            System.out.println(products.size());
            for (Product product : products) {
                System.out.println(product.getName());
            }
        }

        //optimally we can get all the products in a single call as well
        //select * from product P where P.category_Id IN [category list]  => optimal way

    }
}
