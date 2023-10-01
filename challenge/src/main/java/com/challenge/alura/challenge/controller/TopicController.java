package com.challenge.alura.challenge.controller;

import com.challenge.alura.challenge.domain.topic.*;
import com.challenge.alura.challenge.infra.exception.ValidationException;
import com.challenge.alura.challenge.infra.exception.ValidationExceptionForbidden;
import com.challenge.alura.challenge.infra.exception.ValidationExceptionNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;


@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateTopicData data) {

        topicService.create(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TopicResponseData>> list(@PageableDefault(size = 10, sort = {"createdAt"}, direction = Sort.Direction.ASC) Pageable pagination,
                                                        @RequestParam(name = "curso", required = false) String courserName,
                                                        @RequestParam(name = "data-criacao", required = false) String createdAt
    ) {
        if (courserName != null && createdAt != null) {
            var topics = this.topicRepository.findByCourseNameAndCreationYear(courserName, Integer.parseInt(createdAt), pagination).map(TopicResponseData::new);
            return ResponseEntity.ok().body(topics);
        }

        if (courserName != null) {
            var topics = this.topicRepository.findByCourseName(courserName, pagination).map(TopicResponseData::new);
            return ResponseEntity.ok().body(topics);
        }
        if (createdAt != null) {
            var topics = this.topicRepository.findByYearCreation(Integer.parseInt(createdAt), pagination).map(TopicResponseData::new);
            return ResponseEntity.ok().body(topics);

        }

        var topics = this.topicRepository.findAll(pagination).map(TopicResponseData::new);
        return ResponseEntity.ok().body(topics);


    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {

        Topic topic = this.topicRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new TopicDetailedData(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateTopicData data) {
        Long userId = 10L;
        try {
            this.topicService.update(id, userId, data);
        } catch (ValidationExceptionNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (ValidationExceptionForbidden e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ValidationException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(@PathVariable Long id) {

        try {
            this.topicService.delete(id, id);
        } catch (AccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }
}
