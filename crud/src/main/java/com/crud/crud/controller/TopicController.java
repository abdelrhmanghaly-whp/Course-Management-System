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
import com.crud.crud.model.Topic;
import com.crud.crud.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public ResponseEntity<Page<Topic>> getAllTopics(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(topicService.getAllTopics(pageable));
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable String id) {
        Topic topic = topicService.getTopic(id);
        return ResponseEntity.ok(topic);
    }

    @PostMapping("/topics")             
    public ResponseEntity<Topic>addTopic(@Valid @RequestBody Topic topic) {
        // to do -> add validation to the topic it may be existed
        topicService.addTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topic>updateTopic(@Valid @RequestBody Topic topic, @PathVariable String id) {
        topicService.updateTopic(topic, id);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Void>deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}

