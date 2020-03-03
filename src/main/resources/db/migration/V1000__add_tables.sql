create table regions
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table employees
(
    id         int auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    region_id  int          null,
    constraint FK5mear4x539nfkn4jpuuvfajk4
        foreign key (region_id) references regions (id)
);

create table languages
(
    id          int auto_increment
        primary key,
    name        varchar(255) null,
    employee_id int          not null,
    constraint FKg1o7loht8euyqb53036c9ie5y
        foreign key (employee_id) references employees (id)
);

create table shifts
(
    id          int auto_increment
        primary key,
    name        varchar(255) null,
    employee_id int          not null,
    constraint FKtbsbc3nmr4b1vlwtnd944q9u7
        foreign key (employee_id) references employees (id)
);

