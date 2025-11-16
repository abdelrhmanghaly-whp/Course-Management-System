package com.crud.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.crud.crud.model.Course;
// import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepository extends CrudRepository<Course, String> {
    
    public Page<Course> findByTopic_Id(String topicId, Pageable pageable);
}

