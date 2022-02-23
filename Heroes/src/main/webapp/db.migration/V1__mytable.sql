CREATE TABLE user (
id bigint unique primary key,
username varchar(64) not null,
password varchar(64) not null,
email varchar(64) not null,
country varchar(64) not null
);

CREATE TABLE hero (
id bigint unique primary key,
name varchar(64) not null,
type varchar(64) not null,
level varchar(64) not null,
user_id bigint not null,
foreign key(user_id) references user(id)
);