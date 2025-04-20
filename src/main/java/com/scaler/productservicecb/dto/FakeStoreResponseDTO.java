package com.scaler.productservicecb.dto;


import com.scaler.productservicecb.models.Category;
import com.scaler.productservicecb.models.Product;
import lombok.Getter;
import lombok.Setter;

// purpose to get response from outside world(FakeStoreAPI).
// we place this inside DTO(Data Transfer Objects).
@Getter
@Setter
public class FakeStoreResponseDTO {
    private String id;
    private String title;
    private Integer price;
    private String description;
    private String image;
    private String category;

//    // Getters
//    public String getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public Integer getPrice() {
//        return price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    // Setters
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setPrice(Integer price) {
//        this.price = price;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public Product toProduct(){
        Product product = new Product();
        product.setId(Long.valueOf(this.id));
        product.setName(this.title);
        product.setPrice(this.price * 1.0);
        product.setDescription(this.description);
        product.setImageUrl(this.image);
        Category category = new Category();
        category.setName(this.category);
        product.setCategory(category);
        return product;
    }

}
