package com.scaler.productservicecb.dto;

import com.scaler.productservicecb.models.Product;
import lombok.Getter;
import lombok.Setter;

//Why this? If every thing goes well, returns product & responseMessage; else, returns product as null & error message;
@Getter
@Setter
public class ProductResponseDTO {
    private Product product;
    private String responseMessage;
//    // Getter for product
//    public Product getProduct() {
//        return product;
//    }
//
//    // Setter for product
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    // Getter for responseMessage
//    public String getResponseMessage() {
//        return responseMessage;
//    }
//
//    // Setter for responseMessage
//    public void setResponseMessage(String responseMessage) {
//        this.responseMessage = responseMessage;
//    }
}
