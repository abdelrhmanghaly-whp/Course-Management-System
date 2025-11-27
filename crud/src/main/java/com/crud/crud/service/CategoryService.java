package com.crud.crud.service;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.crud.model.Category;
import com.crud.crud.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    public Category getCategory(String id){
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: "+id));
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    public void updateCategory(Category category, String id){
        categoryRepository.save(category);
    }
    public void deleteCategory(String id){
        categoryRepository.deleteById(id);
    }


    
}
