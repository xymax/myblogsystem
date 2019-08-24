create databases blogdemo default charset utf8mb4;

use blogdemo

drop table if exists user;
create table user(
id int primary key auto_increment,
name varchar(20),
create_time timestamp
);


drop table if exists article;
create table article(
id int primary key auto_increment,
title varchar(50) not null,
content mediumtext,
user_id int not null ,
foreign key(user_id) references user(id),
create_time timestamp
);