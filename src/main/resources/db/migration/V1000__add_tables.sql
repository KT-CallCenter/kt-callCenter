create table contracts
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table languages
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

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

create table employee_contract
(
    employee_id int not null,
    contract_id int not null,
    primary key (employee_id, contract_id),
    constraint FKh3kdauk0g6lf6r0ac2xxl7c81
        foreign key (employee_id) references employees (id),
    constraint FKheihomg89qcgxbpkmui8mtoqf
        foreign key (contract_id) references contracts (id)
);

create table employee_language
(
    employee_id int not null,
    language_id int not null,
    primary key (employee_id, language_id),
    constraint FK48m6hkn9p466bylb306b6thyp
        foreign key (language_id) references languages (id),
    constraint FKslleotm3pjfpw8ikhpg9houcx
        foreign key (employee_id) references employees (id)
);

