package com.challenge.alura.challenge.controller;

import com.challenge.alura.challenge.domain.topic.CreateTopicData;
import com.challenge.alura.challenge.domain.topic.TopicRepository;
import com.challenge.alura.challenge.domain.topic.TopicResponseData;
import com.challenge.alura.challenge.domain.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    public ResponseEntity<Page<TopicResponseData>> list(@PageableDefault(size = 10, sort = {"createdAt"}, direction =  Sort.Direction.ASC) Pageable pagination,
                                     @RequestParam(name = "curso", required = false) String courserName,
                                     @RequestParam(name = "data-criacao", required = false) String createdAt
    ) {
        if(courserName != null && createdAt!= null) {
            var topics = this.topicRepository.findByCourseNameAndCreationYear(courserName,Integer.parseInt(createdAt), pagination).map(TopicResponseData::new);
            return ResponseEntity.ok().body(topics);
        }

        if(courserName != null) {
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
}
