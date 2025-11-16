package com.crud.crud.service;

import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.crud.model.Topic;
import com.crud.crud.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    
    public Page<Topic> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }
    
    public Topic getTopic(String id){
        return topicRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Topic not found with id: " + id));
    }
    
    public void addTopic(Topic topic){
        topicRepository.save(topic);
    }
    
    public void updateTopic(Topic topic, String id){
        topicRepository.save(topic);
    }
    
    public void deleteTopic(String id){
        topicRepository.deleteById(id);
    }
}

