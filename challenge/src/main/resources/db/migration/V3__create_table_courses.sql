create table courses (
    id bigint not null auto_increment,
    name  varchar(180) not null,
    description varchar(255) not null,

    primary key (id)
)