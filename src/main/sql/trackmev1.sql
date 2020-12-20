/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.5.62 : Database - trackme
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`trackme` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `trackme`;

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `ownerid` int(10) NOT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `createdon` date DEFAULT NULL,
  `avatar` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `groups` */

insert  into `groups`(`id`,`name`,`ownerid`,`phone`,`createdon`,`avatar`) values 
(1,'my family(jayant K-9716529094)',1,'9716529094','2020-12-21',NULL),
(2,'CUG(jaya kumar-9551002918)',2,'9551002918','2020-12-21',NULL);

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `priority` bit(1) DEFAULT b'0',
  `message` varchar(400) COLLATE utf8_bin DEFAULT NULL,
  `attachment` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `notification` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `active` bit(1) DEFAULT b'0',
  `deleted` bit(1) DEFAULT b'0',
  `otp` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `createdon` date DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `contact` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `deviceid` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `devicename` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `avatar` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `logindate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`email`,`phone`,`password`,`name`,`active`,`deleted`,`otp`,`gender`,`dob`,`createdon`,`city`,`country`,`contact`,`deviceid`,`devicename`,`avatar`,`logindate`) values 
(1,NULL,'9716529094','Welcome@0','jayant K','\0','\0','ec3d0d',NULL,NULL,'2020-12-20',NULL,NULL,'9716529094','3213-34243-23455-3321','Moto',NULL,NULL),
(2,NULL,'9551002918','Welcome@0','jaya kumar','','\0','20ce09',NULL,NULL,'2020-12-21',NULL,'India','9551002918','3213-4534-4563-1234','Samsung',NULL,NULL);

/*Table structure for table `usergroup` */

DROP TABLE IF EXISTS `usergroup`;

CREATE TABLE `usergroup` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `groupid` int(10) NOT NULL,
  `approved` bit(1) DEFAULT b'0',
  `createdon` date DEFAULT NULL,
  `createdby` int(10) NOT NULL,
  `approvedby` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `usergroup` */

/*Table structure for table `userlocation` */

DROP TABLE IF EXISTS `userlocation`;

CREATE TABLE `userlocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `latitude` varchar(20) COLLATE utf8_bin NOT NULL,
  `longitude` varchar(20) COLLATE utf8_bin NOT NULL,
  `createdon` datetime NOT NULL,
  `deviceid` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `userlocation` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
