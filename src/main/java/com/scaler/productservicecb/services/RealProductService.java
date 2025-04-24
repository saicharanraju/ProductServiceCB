package com.scaler.productservicecb.services;

import com.scaler.productservicecb.dto.FakeStoreRequestDTO;
import com.scaler.productservicecb.exceptions.ProductNotFoundException;
import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import com.scaler.productservicecb.repository.CategoryRepository;
import com.scaler.productservicecb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("RealDBProductService")
public class RealProductService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public RealProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(String productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(Long.parseLong(productId));
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
         return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        List<Product> response = productRepository.getProductByCategoryName(categoryName);
        return response;
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        List<Product> productsFromDB = productRepository.getProductByName(searchText);

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
        //goal is to map category reference to product table
        //1. get the category object from DB.
        //2. put this object inside product.
        //3. save the product.
        //4. sometimes we do not get category object. so, use optional.
        /*
        {
           "name":  "dell 15",
           "price": "10000",
           "desc":  "Good",
           "category":{
             "id" : "null",
             "name" : "laptop"
           }
         }
         */
        //product.getCategory() gives me the object, in the Category we use getName()
        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(product.getCategory().getName());
        if(optionalCategory.isEmpty()){ // If no category in the table
            Category category = new Category(); // create new category
            category.setName(product.getCategory().getName()); //give it a name, not id because if we give id, it will update. here we want to create new one.
            Category savedCategoryObject = categoryRepository.save(category); // this returns an id = 4
            product.setCategory(savedCategoryObject); // now, we will update the request with id
            /*
        {
           name:  dell 15"
           price: 10000
           desc:  Good
           Category{
             id = 4,
             name = laptop
           }
        }
         */
            // now we save this to product table

        }else{ //if object is already present
            /*
            Category{
               id = 4,
               name = laptop
            }
             */
            Category alreadyPresentCategory = optionalCategory.get();
            product.setCategory(alreadyPresentCategory);

        }
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        return null;
    }
}
