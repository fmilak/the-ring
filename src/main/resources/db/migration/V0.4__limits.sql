drop table UPLOAD_LIMIT;

alter table USER add column max_limit bigint;
alter table USER add column current_limit bigint;