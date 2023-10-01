package com.challenge.alura.challenge.domain.topic;

import com.challenge.alura.challenge.domain.course.Course;
import com.challenge.alura.challenge.domain.course.CourseRepository;
import com.challenge.alura.challenge.domain.user.student.Student;
import com.challenge.alura.challenge.domain.user.student.StudentRepository;
import com.challenge.alura.challenge.infra.exception.ValidationException;
import com.challenge.alura.challenge.infra.exception.ValidationExceptionForbidden;
import com.challenge.alura.challenge.infra.exception.ValidationExceptionNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;


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
        if (message) {
            throw new ValidationException("Não foi possível criar o novo tópico, pois já existe um tópico semelhante.");
        }

        Student student = studentRepository.getReferenceById(data.authorId());
        Course course = courseRepository.getReferenceById(data.courseId());

        if (!studentRepository.existsById(data.authorId())) {
            throw new ValidationException("Não é possível cadastrar um tópico para um usuário inexistente!");
        }


        if (!courseRepository.existsById(data.courseId())) {
            throw new ValidationException("Não é possível cadastrar um tópico para um curso inexistente!");
        }
        var topic = new Topic(null, data.title(), data.message(), null, student, course);
        topicRepository.save(topic);

    }

    public void update(Long id, Long userId, UpdateTopicData data) throws ValidationExceptionNotFound, ValidationExceptionForbidden {
        var topicExist = this.topicRepository.existsById(id);

        if (!topicExist) {
            throw new ValidationExceptionNotFound("Tópico não encontrado");
        }

        var topic = this.topicRepository.getReferenceById(id);


        var topicMessageAlreadyExists = this.topicRepository.existsByMessage(data.message());

        if (topicMessageAlreadyExists) {
            throw new ValidationException("Já existe um tópico com esse mesmo conteúdo!");
        }

        topic.setMessage(data.message());

    }

    public void delete(Long id, Long userId) throws AccessDeniedException {
        var topicExists = this.topicRepository.existsById(id);

        if (!topicExists) {
            throw new EntityNotFoundException("O usuário não existe");
        }

        var topic = this.topicRepository.getReferenceById(id);

        if (!topic.getAuthor().getId().equals(userId)) {
            throw new AccessDeniedException("O usuário não tem permissão para deletar esse tópico!");
        }

        topicRepository.delete(topic);


    }
}
