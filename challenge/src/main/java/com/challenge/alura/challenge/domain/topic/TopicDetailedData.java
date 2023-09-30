package com.challenge.alura.challenge.domain.topic;

import java.util.Date;

public record TopicDetailedData(String title, String message, Date createdAt, String author, String course ) {
    public TopicDetailedData(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreatedAt(), topic.getAuthor().getName(), topic.getCourse().getName());
    }
}
