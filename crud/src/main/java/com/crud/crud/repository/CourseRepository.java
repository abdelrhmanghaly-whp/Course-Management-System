package com.crud.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.crud.crud.model.Course;
// import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepository extends CrudRepository<Course, String> {
    
    public Page<Course> findByTopic_Id(String topicId, Pageable pageable);
    public Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
    /*
       SELECT * FROM course 
       WHERE LOWER(name) LIKE LOWER('%keyword%')
       LIMIT 10 OFFSET 0
     */
    public Page<Course> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description, Pageable pageable);

}

