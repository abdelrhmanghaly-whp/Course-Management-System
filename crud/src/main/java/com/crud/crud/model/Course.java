package com.crud.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;
// import jakarta.validation.constraints.NotNull;

@Entity
public class Course {

    @Id
    @NotBlank(message = "Id is required")
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    
    @ManyToOne
    private Topic topic;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "course_category", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns =@JoinColumn(name = "category_id"))

    private Set<Category> categories = new HashSet<>();

    public Course(){

    }

    public Course(String id, String name, String description, String topicId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = new Topic(topicId, "", "");
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
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public Set<Category> getCategories(){
        return categories;
    }
    public void addCategory(Category category){
        this.categories.add(category);
        category.getCourses().add(this);
    }
    public void removeCategory(Category category){
        this.categories.remove(category);
        category.getCourses().remove(this);
    }
}

