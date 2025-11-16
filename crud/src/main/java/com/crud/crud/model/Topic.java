package com.crud.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Topic {

    @Id
    @NotBlank(message="Id is required")
    private String id;
    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Description is required")
    private String description;

    public Topic(){

    }

    public Topic(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
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
}

