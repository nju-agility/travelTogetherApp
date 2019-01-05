create database travel;
use travel;
-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: travel
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activities`
--

DROP TABLE IF EXISTS `activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activities` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 是刚创建\n1 是已经验证发布\n3 是已经撤销\n4 是正在进行中\n5 结束',
  `owner` varchar(32) NOT NULL DEFAULT '',
  `city` varchar(10) NOT NULL DEFAULT '南京',
  `location` varchar(45) NOT NULL DEFAULT '鼓楼',
  `title` varchar(45) NOT NULL,
  `details` varchar(200) NOT NULL,
  `time_start` varchar(20) NOT NULL DEFAULT '2019-01-01',
  `time_end` varchar(20) NOT NULL DEFAULT '2020-01-01',
  `score` tinyint(3) unsigned NOT NULL DEFAULT '100',
  `num_of_score` smallint(5) unsigned NOT NULL DEFAULT '1',
  `type` varchar(20) NOT NULL DEFAULT 'outgoing',
  PRIMARY KEY (`aid`),
  UNIQUE KEY `idactivities_UNIQUE` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` VALUES (0,0,'lx','南京','鼓楼','not a activity','just for foregin key','2019-01-01','2048',100,1,'outgoing');
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_tag`
--

DROP TABLE IF EXISTS `activity_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_tag` (
  `aid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  KEY `fk_activity_tag_a_idx` (`aid`),
  KEY `fk_activity_tag_t_idx` (`tid`),
  CONSTRAINT `fk_activity_tag_a` FOREIGN KEY (`aid`) REFERENCES `activities` (`aid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_activity_tag_t` FOREIGN KEY (`tid`) REFERENCES `tags` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_tag`
--

LOCK TABLES `activity_tag` WRITE;
/*!40000 ALTER TABLE `activity_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relative_paths`
--

DROP TABLE IF EXISTS `relative_paths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relative_paths` (
  `item` int(11) NOT NULL COMMENT '0 用户头像\n1 用户身份证\n2 用户拍摄照片\n3 风景照片\n',
  `path` varchar(200) NOT NULL COMMENT '父路径',
  PRIMARY KEY (`item`),
  UNIQUE KEY `item_UNIQUE` (`item`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relative_paths`
--

LOCK TABLES `relative_paths` WRITE;
/*!40000 ALTER TABLE `relative_paths` DISABLE KEYS */;
/*!40000 ALTER TABLE `relative_paths` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(20) NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_applies`
--

DROP TABLE IF EXISTS `user_applies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_applies` (
  `aid` int(11) NOT NULL,
  `account` varchar(32) NOT NULL,
  PRIMARY KEY (`aid`,`account`),
  KEY `fk_user_applies_2_idx` (`account`),
  CONSTRAINT `fk_user_applies_1` FOREIGN KEY (`aid`) REFERENCES `activities` (`aid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_applies_2` FOREIGN KEY (`account`) REFERENCES `users` (`account`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_applies`
--

LOCK TABLES `user_applies` WRITE;
/*!40000 ALTER TABLE `user_applies` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_applies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_comments`
--

DROP TABLE IF EXISTS `user_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_comments` (
  `account_commenter` varchar(32) NOT NULL,
  `account_commented` varchar(32) NOT NULL,
  `comment` varchar(45) NOT NULL DEFAULT 'good',
  `score` tinyint(3) unsigned NOT NULL DEFAULT '100',
  PRIMARY KEY (`account_commenter`,`account_commented`),
  KEY `fk_user_commented_idx` (`account_commented`),
  CONSTRAINT `fk_user_commented` FOREIGN KEY (`account_commented`) REFERENCES `users` (`account`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_commenter` FOREIGN KEY (`account_commenter`) REFERENCES `users` (`account`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comments`
--

LOCK TABLES `user_comments` WRITE;
/*!40000 ALTER TABLE `user_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_records`
--

DROP TABLE IF EXISTS `user_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_records` (
  `account` varchar(32) NOT NULL,
  `aid` int(11) NOT NULL,
  `comment` varchar(200) NOT NULL DEFAULT '',
  `num_of_pic` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`account`,`aid`),
  KEY `fk_records_1_idx` (`aid`),
  CONSTRAINT `fk_record_uid` FOREIGN KEY (`account`) REFERENCES `users` (`account`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_records_1` FOREIGN KEY (`aid`) REFERENCES `activities` (`aid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_records`
--

LOCK TABLES `user_records` WRITE;
/*!40000 ALTER TABLE `user_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tags`
--

DROP TABLE IF EXISTS `user_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_tags` (
  `account` varchar(32) NOT NULL,
  `tid` int(11) NOT NULL,
  KEY `fk_user_tags_u_idx` (`account`),
  KEY `fk_user_tags_t_idx` (`tid`),
  CONSTRAINT `fk_user_tags_t` FOREIGN KEY (`tid`) REFERENCES `tags` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_tags_u` FOREIGN KEY (`account`) REFERENCES `users` (`account`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tags`
--

LOCK TABLES `user_tags` WRITE;
/*!40000 ALTER TABLE `user_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `account` varchar(32) NOT NULL COMMENT 'email',
  `name` varchar(10) NOT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '0',
  `age` tinyint(4) DEFAULT '18',
  `in` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 表示未进入活动\n1 表示进入',
  `activity_id` int(11) NOT NULL DEFAULT '0' COMMENT '当前参加的活动aid\n0 在申请\n其他',
  `city` varchar(10) NOT NULL DEFAULT '南京',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '-1 停用状态\n0 初始注册状态，需要验证身份\n1 已经上传照片，等待验证\n2 身份验证成功 ',
  `code` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `passwd` varchar(100) NOT NULL,
  `num_of_score` int(10) unsigned NOT NULL DEFAULT '1',
  `score` tinyint(3) unsigned NOT NULL DEFAULT '100',
  PRIMARY KEY (`account`),
  UNIQUE KEY `id_UNIQUE` (`account`),
  KEY `fk_user_activity_idx` (`activity_id`),
  CONSTRAINT `fk_user_activity` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`aid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-05 18:28:53
