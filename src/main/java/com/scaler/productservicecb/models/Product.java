package com.scaler.productservicecb.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel{
    //All these are primitive datatypes
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Double discount;
    //This is non-primitive
    //I have to define relation between Product & Category.
    //=>Cardinality
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) //when you create Product it should cascade the changes and create category. when you delete it should stop
    @JsonBackReference  // Child side //perplexity
    private Category category;



//    // No-argument constructor
//    public Product() {
//    }
//
//    // All-argument constructor
//    public Product(String id, String name, String description, double price, String imageUrl, Category category) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.imageUrl = imageUrl;
//        this.category = category;
//    }
//
//    // Getters
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    // Setters
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
}
