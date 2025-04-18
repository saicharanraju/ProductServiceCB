package com.scaler.productservicecb.dto;

import lombok.Getter;
import lombok.Setter;

//It's purpose is to accept the request from FE(postman,browser)
@Getter
@Setter
public class FakeStoreRequestDTO {
   /*

"id": 0,
"title": "string",
"price": 0.1,
"description": "string",
"category": "string",
"image": "http://example.com"

    */
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

// // Getters
// public String getTitle() {
//  return title;
// }
//
// public Double getPrice() {
//  return price;
// }
//
// public String getDescription() {
//  return description;
// }
//
// public String getImage() {
//  return image;
// }
//
// public String getCategory() {
//  return category;
// }
//
// // Setters
// public void setTitle(String title) {
//  this.title = title;
// }
//
// public void setPrice(Double price) {
//  this.price = price;
// }
//
// public void setDescription(String description) {
//  this.description = description;
// }
//
// public void setImage(String image) {
//  this.image = image;
// }
//
// public void setCategory(String category) {
//  this.category = category;
// }
}
