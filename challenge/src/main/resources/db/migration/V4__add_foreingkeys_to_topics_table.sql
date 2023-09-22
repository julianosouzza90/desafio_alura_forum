alter table topics
    ADD constraint fk_author
        foreign key (author_id)
            references students(id);

alter table topics
    ADD constraint fk_course
        foreign key (course_id)
            references courses(id);
