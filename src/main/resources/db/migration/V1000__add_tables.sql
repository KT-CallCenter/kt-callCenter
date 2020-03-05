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

create table shifts
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table employee_shift
(
    employee_id int not null,
    shift_id    int not null,
    primary key (employee_id, shift_id),
    constraint FK1eyd9qwku8re162ja50al6pap
        foreign key (employee_id) references employees (id),
    constraint FKcex2xfqg95o9m15hjju7rrp9
        foreign key (shift_id) references shifts (id)
);

