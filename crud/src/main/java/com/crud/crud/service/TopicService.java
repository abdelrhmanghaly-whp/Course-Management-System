package com.crud.crud.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.crud.model.Topic;
import com.crud.crud.repository.TopicRepository;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    
    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
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

