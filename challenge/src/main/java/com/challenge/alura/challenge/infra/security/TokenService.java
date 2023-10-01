package com.challenge.alura.challenge.infra.security;

import com.challenge.alura.challenge.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${api.security.jwt.secret")
    private String secret;

    public String generate(User user) {
        return "";
    }
}
