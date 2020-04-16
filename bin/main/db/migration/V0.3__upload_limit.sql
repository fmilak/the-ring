create table upload_limit
(
    user_uuid varchar2(255) primary key not null references USER(UUID),
    max bigint not null,
    current bigint not null
);

insert into upload_limit (user_uuid, max, current) values
( '4d2793c4-6550-4741-bb12-7c997597d854', 3, 0 );