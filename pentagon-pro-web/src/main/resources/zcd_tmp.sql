-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 192.168.1.203    Database: zcd_tmp
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_permission`
--

DROP TABLE IF EXISTS `menu_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_permission` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) NOT NULL,
  `uri` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_permission`
--

LOCK TABLES `menu_permission` WRITE;
/*!40000 ALTER TABLE `menu_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_permission`
--

DROP TABLE IF EXISTS `resource_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_permission` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(20) NOT NULL,
  `resource_group_id` bigint(20) NOT NULL,
  `uris` varchar(300) NOT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_permission`
--

LOCK TABLES `resource_permission` WRITE;
/*!40000 ALTER TABLE `resource_permission` DISABLE KEYS */;
INSERT INTO `resource_permission` VALUES (1,'用户管理',5,'/system/user,/system/user/add,/systme/user/isNotExist,/system/user/edit'),(2,'资源权限管理1',5,'/system/resourcePermission,/system/resourcePermission/add,/system/resourcePermission/isNotExist,/system/resourcePermission/edit');
/*!40000 ALTER TABLE `resource_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_permission_group`
--

DROP TABLE IF EXISTS `resource_permission_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_permission_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_permission_group`
--

LOCK TABLES `resource_permission_group` WRITE;
/*!40000 ALTER TABLE `resource_permission_group` DISABLE KEYS */;
INSERT INTO `resource_permission_group` VALUES (3,'借款管理'),(4,'还款管理'),(5,'系统管理6');
/*!40000 ALTER TABLE `resource_permission_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  `enable` int(11) NOT NULL COMMENT '角色',
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (5,'总裁','就是公司的老板啦',1,'2016-03-31 16:34:48',NULL),(6,'副总裁','',1,'2016-03-31 16:56:22',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_type` int(11) NOT NULL,
  `permission_ids` text COMMENT '权限ID，各URI间用英文逗号(,)隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `enable` int(11) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(50) DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','96e79218965eb72c92a549dd5a330112','管理员',1,'admin@admin.com','18688888888',NULL,1,'2016-03-23 13:30:37','0:0:0:0:0:0:0:1',NULL,'2016-03-21 18:20:30',NULL),(2,'test','96e79218965eb72c92a549dd5a330112',NULL,6,'test@z1i.com','',NULL,1,NULL,NULL,NULL,'2016-03-29 15:57:54',NULL),(3,'zhu','96e79218965eb72c92a549dd5a330112',NULL,6,'z@h.u','',NULL,1,NULL,NULL,NULL,'2016-03-29 18:36:04',NULL),(4,'t1','96e79218965eb72c92a549dd5a330112',NULL,6,'r1@jls.om','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:06:06',NULL),(5,'t2','96e79218965eb72c92a549dd5a330112',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:06:36',NULL),(6,'t3','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:06:48',NULL),(7,'t4','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:06:59',NULL),(8,'t5','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:07:09',NULL),(9,'t6','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:07:21',NULL),(10,'t7','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:07:30',NULL),(11,'t8','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:07:52',NULL),(12,'t9','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:08:04',NULL),(13,'t10','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','',NULL,1,NULL,NULL,NULL,'2016-04-01 15:08:20',NULL),(14,'t11','b34d76a07a0d8b6fcab44d094761efde',NULL,6,'test@zi.com','1',NULL,1,NULL,NULL,NULL,'2016-04-01 15:08:32','2016-04-05 16:04:28');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-05 18:06:40
