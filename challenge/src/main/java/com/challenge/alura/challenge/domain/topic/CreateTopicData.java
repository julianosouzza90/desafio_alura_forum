package com.challenge.alura.challenge.domain.topic;

import com.challenge.alura.challenge.domain.course.Course;
import com.challenge.alura.challenge.domain.student.Student;
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
