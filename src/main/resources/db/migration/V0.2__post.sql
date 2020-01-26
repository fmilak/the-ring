create table post
(
	id bigint identity not null primary key,
	user_uuid varchar2(255) not null,
	text varchar2(255),
	picture blob
);

ALTER TABLE post
    ADD FOREIGN KEY (user_uuid)
    REFERENCES user (uuid);

create table likes
(
    id bigint identity not null primary key,
    user_uuid varchar2(255) not null,
    post_id bigint not null,
    liked boolean,
    disliked boolean
);

ALTER TABLE likes
    ADD FOREIGN KEY (user_uuid)
    REFERENCES user (uuid);

ALTER TABLE likes
    ADD FOREIGN KEY (post_id)
    REFERENCES post (id);