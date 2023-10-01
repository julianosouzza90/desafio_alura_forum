package com.challenge.alura.challenge.domain.user.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public Student create(StudentCreateData data) {

        this.studentRepository.existsByLogin(data.email());

        return  null;

    }
}
