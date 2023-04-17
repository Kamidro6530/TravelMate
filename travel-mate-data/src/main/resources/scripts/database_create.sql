drop table if exists destinations;
drop table if exists itineraries;
drop table if exists users;
create table destinations (id bigint not null auto_increment, country varchar(255), currency varchar(255), description varchar(255), image varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table itineraries (id bigint not null auto_increment, end_date date, notes varchar(255), start_date date, destination_id bigint, user_id bigint, primary key (id)) engine=InnoDB;
create table users (id bigint not null auto_increment, birth_date date, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id)) engine=InnoDB;
alter table itineraries add constraint FKjof330tfrq1p882rcqnwovrq0 foreign key (destination_id) references destinations (id);
alter table itineraries add constraint FKedy4gxkhapn2hpovc9899u3vt foreign key (user_id) references users (id);