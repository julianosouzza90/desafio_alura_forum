package com.challenge.alura.challenge.controller;

import com.challenge.alura.challenge.infra.security.UserAuthenticateData;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public class Authenticate {
    @PostMapping
    public void login(@RequestBody @Valid UserAuthenticateData data) {

    }
}
