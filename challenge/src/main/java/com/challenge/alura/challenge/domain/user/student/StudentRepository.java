package com.challenge.alura.challenge.domain.user.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByLogin(String email);
}
