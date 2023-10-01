create table topics
(

    id         bigint       not null auto_increment,
    title      varchar(180) not null,
    message    text         not null,
    created_at timestamp default current_timestamp(),
    status     int       default 1,
    author_id  bigint       not null,
    course_id  bigint       not null,

    PRIMARY KEY (id)
);