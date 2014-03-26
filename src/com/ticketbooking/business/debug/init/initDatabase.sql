drop database if exists `ticketbooking`;
create database `ticketbooking` charset=utf8 collate=utf8_general_ci;

use ticketbooking;

create table `p_user`(
	`userId` bigint not null AUTO_INCREMENT,
	`password` varchar(35) not null,
	primary key(`userId`)
)default character set=utf8 collate=utf8_general_ci engine=InnoDB;