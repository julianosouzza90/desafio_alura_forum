package com.challenge.alura.challenge.domain.topic;

import com.challenge.alura.challenge.domain.course.Course;
import com.challenge.alura.challenge.domain.student.CourseRepository;
import com.challenge.alura.challenge.domain.student.Student;
import com.challenge.alura.challenge.domain.student.StudentRepository;
import com.challenge.alura.challenge.infra.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.file.AccessDeniedException;
import java.util.List;

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
        var topic = new Topic(null, data.title(), data.message(), null,student, course);
        topicRepository.save(topic);

    }


    public void delete(Long id, Long userId) throws AccessDeniedException {
        var topicExists = this.topicRepository.existsById(id);

        if(!topicExists) {
            throw  new EntityNotFoundException("O usuário não existe");
        }

        var topic = this.topicRepository.getReferenceById(id);

        if(!topic.getAuthor().getId().equals(userId)) {
            throw  new AccessDeniedException("O usuário não tem permissão para deletar esse tópico!");
        }

        topicRepository.delete(topic);


    }
}
