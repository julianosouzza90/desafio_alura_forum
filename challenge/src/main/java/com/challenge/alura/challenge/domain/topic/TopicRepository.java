package com.challenge.alura.challenge.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
     boolean existsByTitleAndMessage(String title, String message);

    Page<Topic> findByCourseName(String courseName, Pageable pagination);

    @Query("SELECT t FROM Topic t  WHERE YEAR(t.createdAt) = :year")
    Page<Topic> findByYearCreation(@Param("year") Integer CreatedYear, Pageable pagination);
    @Query("SELECT  t from Topic  t WHERE YEAR(t.createdAt) = :year AND t.course.name = :courseName")
    Page<Topic> findByCourseNameAndCreationYear(@Param("courseName") String courserName,@Param("year") Integer CreatedYear, Pageable pagination);

    boolean existsByMessage(String message);
}
