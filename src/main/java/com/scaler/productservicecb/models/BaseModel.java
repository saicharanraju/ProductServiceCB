package com.scaler.productservicecb.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//this class is not going to be a table. So, no Entity.
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // IDENTITY = AutoIncrement
    // AUTO = unique value but random
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;

}