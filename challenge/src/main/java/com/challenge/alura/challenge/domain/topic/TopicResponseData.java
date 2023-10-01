package com.challenge.alura.challenge.domain.topic;

import com.challenge.alura.challenge.domain.course.Course;

import java.util.Date;

public record TopicResponseData(String título, String mensagem, Date createdAt, Long authorId, String authorName,
                                Course curso) {
    public TopicResponseData(Topic topic) {
        this(
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreatedAt(),
                topic.getAuthor().getId(),
                topic.getAuthor().getName(),
                topic.getCourse());
    }
}
