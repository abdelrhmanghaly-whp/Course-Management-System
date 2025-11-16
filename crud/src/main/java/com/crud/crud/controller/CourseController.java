package com.crud.crud.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
// import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.crud.model.Course;
import com.crud.crud.model.Topic;
import com.crud.crud.service.CourseService;
import com.crud.crud.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private TopicService topicService;

    @GetMapping("/topics/{topicId}/courses")
public ResponseEntity<Page<Course>> getAllCourses(
    @PathVariable String topicId,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
) {
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(courseService.getAllCourses(topicId, pageable));
}
    

    @GetMapping("/topics/{topicId}/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @PostMapping("/topics/{topicId}/courses")
    public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course, @PathVariable String topicId) {
        Topic topic = topicService.getTopic(topicId);
        course.setTopic(topic);
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping("/topics/{topicId}/courses/{id}")
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course, @PathVariable String topicId, @PathVariable String id) {
        Topic topic = topicService.getTopic(topicId);
        course.setTopic(topic);
        courseService.updateCourse(course);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/topics/{topicId}/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

