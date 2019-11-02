CREATE SCHEMA IF NOT EXISTS dev;

create table dev.user
(
	uuid varchar2(255) not null,
	password varchar2(255) not null,
	name varchar2(255) not null,
	surname varchar2(255) not null,
	email varchar2(255),
);

create unique index user_uuid_uindex
	on dev.user (uuid);

alter table dev.user
	add constraint user_pk
		primary key (uuid);

insert into dev.user (uuid, password, name, surname, email)
    values ('4d2793c4-6550-4741-bb12-7c997597d854', 'admin', 'admin', 'admin', 'admin');