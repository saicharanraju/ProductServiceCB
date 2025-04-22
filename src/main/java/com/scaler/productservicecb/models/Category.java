package com.scaler.productservicecb.models;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Category extends BaseModel{

    private String name;

    //it is inverse of the same relation between Product and Category
    //this relation is already handled by category column in Product table
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;


//    // No-argument constructor
//    public Category() {
//    }
//
//    // All-argument constructor
//    public Category(String id, String name) {
//        this.id = id;
//        this.name = name;
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
//    // Setters
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
