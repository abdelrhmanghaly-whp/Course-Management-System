package com.crud.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.crud.crud.model.Course;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
    
    public List<Course> findByTopic_Id(String topicId);
}

