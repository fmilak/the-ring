create table user
(
	uuid varchar2(255) not null,
	username varchar2(255) not null,
	password varchar2(255) not null,
	name varchar2(255) not null,
	surname varchar2(255) not null,
	email varchar2(255),
	role varchar2(255) not null
);

create unique index user_uuid_uindex
	on user (uuid);

alter table user
	add constraint user_pk
		primary key (uuid);

insert into user (uuid, username, password, name, surname, email, role)
    values ('4d2793c4-6550-4741-bb12-7c997597d854', 'admin', '$2y$12$QmMMTNIGAPkUhlW3SWCJ3e8ynvLIPIuLNvOut0z36z/WmzV6fNj1O', 'admin', 'admin', 'admin', 'ADMIN');
