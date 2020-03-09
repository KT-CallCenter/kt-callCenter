create table languages
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table regions
(
    id            int auto_increment
        primary key,
    name          varchar(255) null,
    working_hours int          null
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

create table shift_times
(
    id         int auto_increment
        primary key,
    end_time   time null,
    start_time time null,
    region_id  int  null,
    shift_id   int  null,
    constraint FK8y2s5n9rkuqesx42j0dsi2pqm
        foreign key (region_id) references regions (id),
    constraint FKjr8x80eid1w8e817cljbevhu0
        foreign key (shift_id) references shifts (id)
);

create table rosters
(
    id            int auto_increment
        primary key,
    date          date null,
    shift_time_id int  null,
    constraint FK2gtb6c2dimjjxhv9imdsbmwfl
        foreign key (shift_time_id) references shift_times (id)
);

create table rosters_employees
(
    roster_id    int not null,
    employees_id int not null,
    primary key (roster_id, employees_id),
    constraint UK_14wavtrg5sap9yf2yd779n2oq
        unique (employees_id),
    constraint FK65sw4n65fd4cdnq65gqiioh2v
        foreign key (employees_id) references employees (id),
    constraint FKi19ug9q554b3rce4eav53bp76
        foreign key (roster_id) references rosters (id)
);

