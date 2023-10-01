package com.challenge.alura.challenge.domain.user.student;

public record StudentDetailedData(Long id, String name, String login) {
    public StudentDetailedData(Student student) {
        this(student.getId(), student.getName(), student.getLogin());
    }
}
