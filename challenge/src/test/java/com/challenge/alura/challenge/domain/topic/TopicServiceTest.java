package com.challenge.alura.challenge.domain.topic;

import com.challenge.alura.challenge.domain.course.Course;
import com.challenge.alura.challenge.domain.student.CourseRepository;
import com.challenge.alura.challenge.domain.student.Student;
import com.challenge.alura.challenge.domain.student.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import static org.mockito.Mockito.*;

public class TopicServiceTest {

    @InjectMocks
    TopicService topicService;

    @Mock
    TopicRepository topicRepository;

    Topic topic;

    CreateTopicData createTopicData;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        topic = new Topic(1L,"topic test","topic body", null,new Student(),new Course());

        createTopicData = new CreateTopicData(topic.getTitle(), topic.getMessage(), 1L, 1L);

    }

    @Test
    @DisplayName("Should be create a new topic")
    void createNewTopic() {
        when(topicRepository.existsByTitleAndMessage(topic.getTitle(), topic.getMessage())).thenReturn(false);
        when(studentRepository.existsById(1L)).thenReturn(true);
        when(courseRepository.existsById(1L)).thenReturn(true);

        topicService.create(createTopicData);

        verify(topicRepository).existsByTitleAndMessage(topic.getTitle(), topic.getMessage());
        verify(topicRepository, times(1)).save(new Topic())

;    }



}
