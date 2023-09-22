package com.challenge.alura.challenge.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
     boolean existsByTitleAndMessage(String title, String message);
}
