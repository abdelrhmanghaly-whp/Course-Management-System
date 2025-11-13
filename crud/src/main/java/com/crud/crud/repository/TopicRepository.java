package com.crud.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.crud.crud.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {
    
}

