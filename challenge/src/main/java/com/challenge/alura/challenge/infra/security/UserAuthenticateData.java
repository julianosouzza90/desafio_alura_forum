package com.challenge.alura.challenge.infra.security;

import jakarta.validation.constraints.NotNull;

public record UserAuthenticateData(
        @NotNull
        String login,
        @NotNull
        String password
) {
}
