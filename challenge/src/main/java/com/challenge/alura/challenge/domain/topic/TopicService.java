package com.challenge.alura.challenge.domain.topic;

import com.challenge.alura.challenge.domain.CreateTopicData;

import com.challenge.alura.challenge.domain.course.Course;
import com.challenge.alura.challenge.domain.student.CourseRepository;
import com.challenge.alura.challenge.domain.student.Student;
import com.challenge.alura.challenge.domain.student.StudentRepository;
import com.challenge.alura.challenge.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.notNull;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    public void create(CreateTopicData data) {

        var message = topicRepository.existsByTitleAndMessage(data.title(), data.message());
        if(message) {
            throw  new ValidationException("Não foi possível criar o novo tópico, pois já existe um tópico semelhante.");
        }

        Student student = studentRepository.getReferenceById(data.authorId());
        Course course = courseRepository.getReferenceById(data.courseId());

        if(!studentRepository.existsById(data.authorId())) {
            throw  new ValidationException("Não é possível cadastrar um tópico para um usuário inexistente!");
        }



        if(!courseRepository.existsById(data.courseId())) {
            throw  new ValidationException("Não é possível cadastrar um tópico para um curso inexistente!");
        }
        var topic = new Topic(null, data.title(), data.message(), student, course);
        topicRepository.save(topic);

    }
}
