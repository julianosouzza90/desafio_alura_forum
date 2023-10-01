package com.challenge.alura.challenge.domain.user.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StudentCreateData(
        @NotNull
        String name,
        @NotNull
        @Email
        String email,
        @NotNull
        @Min(8)
        String password
        ){
}
