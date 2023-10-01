package com.challenge.alura.challenge.domain.user.student;

import com.challenge.alura.challenge.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity()
@Table(name = "students")
public class Student extends User {

}
