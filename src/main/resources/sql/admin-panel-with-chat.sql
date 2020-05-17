drop database if exists `admin-panel-with-chat-db`;
create database `admin-panel-with-chat-db`;
use `admin-panel-with-chat-db`;

create table if not exists `admins` (
	`id` int not null auto_increment,
    `name` varchar(50) not null,
    `type`  int not null,
    `username` varchar(50) not null,
    `password` varchar(250) not null,
    `created_at` timestamp default current_timestamp,
    `push_token` varchar(250),
    CONSTRAINT `unique_username` UNIQUE (`username`),
    PRIMARY KEY (`id`)
) auto_increment = 1, engine = 'InnoDB';

create table if not exists `users` (
	`id` int not null auto_increment,
    `name` varchar(50) not null,
    `username` varchar(50) not null,
    `created_at` timestamp default current_timestamp,
    CONSTRAINT `unique_username` UNIQUE (`username`),
    PRIMARY KEY (`id`)
) auto_increment = 1, engine = 'InnoDB';

create table if not exists `message_walls` (
	`id` int not null auto_increment,
    `chat_id` varchar(20) not null,
    `created_at` timestamp default current_timestamp,
    PRIMARY KEY (`id`),
    CONSTRAINT `unique_chat_id` UNIQUE (`chat_id`)
) auto_increment = 1, engine = 'InnoDB';

create table if not exists `messages` (
	`id` int not null auto_increment,
    `sender_id` int not null,
    `wall_id` int not null,
    `content` text,
    PRIMARY KEY (`id`),
    constraint `sender_id_foreign_key_user_id` foreign key (`sender_id`) references users(`id`),
    constraint `wall_id_foreign_key_msg_wall_id` foreign key (`wall_id`) references message_walls(`id`)
) auto_increment = 1, engine = 'InnoDB';