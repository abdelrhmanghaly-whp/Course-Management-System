package com.crud.crud.service;

import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.crud.model.Course;
import com.crud.crud.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import com.crud.crud.service.EmailService;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EmailService emailService;
    
    public Page<Course> getAllCourses(String topicId, Pageable pageable) {
        return courseRepository.findByTopic_Id(topicId, pageable);
    }

    public Page<Course> searchCoursesByName(String keyword, Pageable pageable){
        return courseRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

    public Page<Course> searchCourses(String keyword, Pageable pageable){
        return courseRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword, pageable);
        // name LIKE %keyword% OR description LIKE %keyword%
        // search mrteen in both fields fa pass it twice
    }
    
    public Course getCourse(String id){
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }
    
    public void addCourse(Course course){
        courseRepository.save(course);

        String topicName = course.getTopic()!=null?course.getTopic().getName():"Unkown";
        emailService.sendCourseCreatedNotification(course.getName(), topicName);

    }
    
    public void updateCourse(Course course){
        courseRepository.save(course);

        emailService.sendCourseUpdateNotification(course.getName(), "System");
    }
    
    public void deleteCourse(String id){
        Course course = getCourse(id);
        courseRepository.deleteById(id);
        emailService.sendCourseDeletedNotification(course.getName());
    }

 
}

