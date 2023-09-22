package com.challenge.alura.challenge.controller;

import com.challenge.alura.challenge.domain.CreateTopicData;
import com.challenge.alura.challenge.domain.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateTopicData data) {

        topicService.create(data);
        return ResponseEntity.noContent().build();
    }
}
