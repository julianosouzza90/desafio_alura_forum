package com.challenge.alura.challenge.domain.student;

import com.challenge.alura.challenge.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
