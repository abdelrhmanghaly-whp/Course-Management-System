package com.crud.crud.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import com.crud.crud.model.Topic;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

public interface TopicRepository extends PagingAndSortingRepository<Topic, String>, CrudRepository<Topic, String> {
    
}