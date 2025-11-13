package com.crud.crud.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.crud.model.Course;
import com.crud.crud.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    
    public List<Course> getAllCourses(String topicId) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopic_Id(topicId).forEach(courses::add);
        return courses;
    }
    
    public Course getCourse(String id){
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }
    
    public void addCourse(Course course){
        courseRepository.save(course);
    }
    
    public void updateCourse(Course course){
        courseRepository.save(course);
    }
    
    public void deleteCourse(String id){
        courseRepository.deleteById(id);
    }
}

