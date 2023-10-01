package com.challenge.alura.challenge.domain.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicData(
        @NotNull
        String title,
        @NotNull
        String message) {
}
