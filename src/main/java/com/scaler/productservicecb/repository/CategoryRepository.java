package com.scaler.productservicecb.repository;

import com.scaler.productservicecb.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category); //will have the relevant methods to do CRUD on Category
    Optional<Category> getCategoryByName(String catName);
}
