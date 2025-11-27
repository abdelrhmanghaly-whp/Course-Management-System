package com.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.crud.model.Category;


public interface CategoryRepository extends JpaRepository <Category,String> {

    
}
