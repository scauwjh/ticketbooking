drop database if exists `ticketbooking`;
create database `ticketbooking` charset=utf8 collate=utf8_general_ci;

use ticketbooking;

create table `p_user`(
	`userId` varchar(15) not null,
	`password` varchar(40) not null,
	`token` varchar(35) not null,
	`redirection` varchar(150) not null,
	`createDate` datetime not null,
	primary key(`userId`)
)default character set=utf8 collate=utf8_general_ci engine=InnoDB;