create table students (

    id bigint not null auto_increment,
    name  varchar(180) not null,
    login  varchar(180) not null,
    password  varchar(255) not null,
    created_at timestamp default  current_timestamp(),

    PRIMARY KEY  (id)
);