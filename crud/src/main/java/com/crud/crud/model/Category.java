package com.crud.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @NotBlank(message = "Id is required")
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    private String description;

    @ManyToMany(mappedBy ="categories")
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    public Category(){

    }

    public Category(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Set<Course> getCourses(){
        return courses;
    }
    public void setCourses(Set<Course> courses){
        this.courses = courses;
    }

    


    
}


