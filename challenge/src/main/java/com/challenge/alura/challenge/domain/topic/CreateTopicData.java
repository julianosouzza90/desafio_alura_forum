package com.challenge.alura.challenge.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record CreateTopicData(
        @NotNull
        String title,
        @NotNull
        String message,
        @NotNull
        @JsonAlias("author_id")
        Long authorId,
        @NotNull
        @JsonAlias("course_id")
        Long courseId
) {
}
