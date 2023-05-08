drop table if exists destinations;
drop table if exists itineraries;
drop table if exists users;
drop table if exists roles;
drop table if exists users_role;
create table destinations (id bigint not null auto_increment, country varchar(255), currency varchar(255), description varchar(255), image varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table itineraries (id bigint not null auto_increment, end_date date, notes varchar(255), start_date date, destination_id bigint, user_id bigint, primary key (id)) engine=InnoDB;
create table roles (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table users (id bigint not null auto_increment, birth_date date, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table users_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB;
alter table roles drop index UK_ofx66keruapi6vyqpv6f2or37;
alter table roles add constraint UK_ofx66keruapi6vyqpv6f2or37 unique (name);
alter table itineraries add constraint FKjof330tfrq1p882rcqnwovrq0 foreign key (destination_id) references destinations (id);
alter table itineraries add constraint FKedy4gxkhapn2hpovc9899u3vt foreign key (user_id) references users (id);
alter table users_role add constraint FKeejqlb4gq1av9540jg66ju2pi foreign key (role_id) references roles (id);
alter table users_role add constraint FKqpe36jsen4rslwfx5i6dj2fy8 foreign key (user_id) references users (id);
insert into roles(name) values ('ADMIN');
insert into roles(name) values ('USER');