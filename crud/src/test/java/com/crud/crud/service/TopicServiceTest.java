package com.crud.crud.service;

import com.crud.crud.model.Topic;
import com.crud.crud.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;
    
    @InjectMocks
    private TopicService topicService;
    private Topic testTopic;

    @BeforeEach
    void setUp(){
        testTopic = new Topic("java","Java","Java Programming");
    }


    // lw mogod
    @Test
    void getTopic_WhenTopicExists_ShouldReturnTopic(){
        when(topicRepository.findById("java")).thenReturn(Optional.of(testTopic)); // fake behavior (arrange)

        Topic res=topicService.getTopic("java"); // act 

        assertNotNull(res); // assert
        assertEquals("java",res.getId());
        assertEquals("Java",res.getName());
        verify(topicRepository,times(1)).findById("java"); // verify
    }

    // lw msh mogod
    @Test
    void getTopic_WhenTopicDoesNotExist_ShouldThrowException(){
        // arrange
        when(topicRepository.findById("python")).thenReturn(Optional.empty());

        // act and assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> topicService.getTopic("python"));
        assertTrue(ex.getMessage().contains("Topic not found with id: python"));
        verify(topicRepository,times(1)).findById("python");
    }


    @Test
    void getAllTopics_ShouldReturnPagedTopics(){
        // arrange
        Topic topic2= new Topic("python","Python","Python Programming");
        Page<Topic> topicPage= new PageImpl<>(Arrays.asList(testTopic,topic2));
        Pageable pageable = PageRequest.of(0,10);
        when(topicRepository.findAll(pageable)).thenReturn(topicPage);

        // act
        Page<Topic> res = topicService.getAllTopics(pageable);
        // assert

        assertNotNull(res);
        assertEquals(2,res.getContent().size());
        verify(topicRepository,times(1)).findAll(pageable);
    }


    @Test
    void addTopic_ShouldSaveTopic(){
        // arrange
        when(topicRepository.save(testTopic)).thenReturn(testTopic);
        // act
        topicService.addTopic(testTopic);
        // assert
        verify(topicRepository,times(1)).save(testTopic);
    }

    @Test
    void updateTopic_ShouldUpdateExistingTopic() {
        when(topicRepository.save(testTopic)).thenReturn(testTopic);
        topicService.updateTopic(testTopic, "java");
        verify(topicRepository, times(1)).save(testTopic);
    }

    @Test
    void deleteTopic_ShouldDeleteTopic() {
        doNothing().when(topicRepository).deleteById("java");
        topicService.deleteTopic("java");
        verify(topicRepository, times(1)).deleteById("java");
    }
}


    

