package com.challenge.alura.challenge.controller;

import com.challenge.alura.challenge.domain.user.student.Student;
import com.challenge.alura.challenge.domain.user.student.StudentDetailedData;
import com.challenge.alura.challenge.domain.user.student.StudentCreateData;
import com.challenge.alura.challenge.domain.user.student.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/aluno")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Transactional
    public ResponseEntity<StudentDetailedData> create(@RequestBody @Valid StudentCreateData data, UriComponentsBuilder uriBuilder) {
        try {
            this.studentService.create(data);
            var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(1).toUri();
            Student student = this.studentService.create(data);

            return  ResponseEntity.created(uri).body(new StudentDetailedData(student));
        } catch (RuntimeException e) {
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok().build();
    }
}
