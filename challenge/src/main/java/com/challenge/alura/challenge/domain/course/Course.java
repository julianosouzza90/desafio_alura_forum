package com.challenge.alura.challenge.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY )
        private Long id;

        private String name;

        private String description;


    }
