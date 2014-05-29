drop database if exists `ticketbooking`;
create database `ticketbooking` charset=utf8 collate=utf8_general_ci;

use ticketbooking;

#权限表
create table `p_role`(
	`roleId` tinyint not null,
	`power` tinyint not null default 2,
	`redirection` varchar(150) not null,
	primary key(`roleId`)
)default character set=utf8 collate=utf8_general_ci engine=InnoDB;

#初始化role表
insert into `p_role` value (1, 1, '/inner/cinema/index.jsp');
insert into `p_role` value (2, 2, '/index.jsp');

#用户表
create table `p_user`(
	`id` bigint not null AUTO_INCREMENT,
	`userId` varchar(15) not null,
	`password` varchar(40) not null,
	`token` varchar(35) not null,
	`role` tinyint not null,
	`createDate` datetime not null,
	primary key(`id`),
	foreign key(`role`) references `p_role`(`roleId`) on update cascade on delete cascade,
	index(`role`) using btree
)default character set=utf8 collate=utf8_general_ci engine=InnoDB;

#电影票表
create table `t_ticket`(
	`ticketId` bigint not null,
	`userId` bigint not null,
	`ticketName` varchar(50) not null,
	`ticketPrice` float not null,
	`originalPrice` float not null,
	`onTime` datetime null,
	`country` varchar(50) null,
	`filmType` varchar(255) null,
	`language` varchar(20) null,
	`ticketIntro` text null,
	`ticketImg` varchar(255) null,
	`prevue` varchar(255) null,
	`releaseTime` datetime not null,
	primary key(`ticketId`),
	foreign key(`userId`) references `p_user`(`id`) on update cascade on delete cascade,
	index(`userId`) using btree
)default character set=utf8 collate=utf8_general_ci engine=InnoDB;

#用户信息表
create table `p_user_info`(
	`id` bigint not null AUTO_INCREMENT,
	`userId` bigint not null,
	`name` varchar(50) null,
	`telephone` varchar(15) null,
	`address` varchar(255) null,
	`IDCard` varchar(20) null,
	`otherCard` varchar(20) null,
	primary key(`id`),
	foreign key(`userId`) references `p_user`(`id`) on update cascade on delete cascade,
	index(`userId`) using btree
)default character set=utf8 collate=utf8_general_ci engine=InnoDB;

#用户订票记录
create table `t_ticket_record`(
	`id` bigint not null AUTO_INCREMENT,
	`userId` bigint not null,
	`ticketId` bigint not null,
	`checked` tinyint not null default 0,
	`orderDate` datetime not null,
	primary key(`id`),
	foreign key(`userId`) references `p_user`(`id`) on update cascade on delete cascade,
	index(`userId`) using btree,
	foreign key(`ticketId`) references `t_ticket`(`ticketId`) on update cascade on delete cascade,
	index(`ticketId`) using btree
)default character set=utf8 collate=utf8_general_ci engine=InnoDB;
