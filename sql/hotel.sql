
-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: Hotel
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
SET TIME_ZONE='+08:00' ;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Coupon`
--
use hotel;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Coupon` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `description` varchar(255) DEFAULT NULL,
                          `hotelId` int(11) DEFAULT '-1',
                          `couponType` int(11) NOT NULL,
                          `couponName` varchar(255) NOT NULL,
                          `target_money` int(11) DEFAULT NULL,
                          `discount` double DEFAULT NULL,
                          `status` int(11) DEFAULT NULL,
                          `start_time` datetime DEFAULT NULL,
                          `end_time` datetime DEFAULT NULL,
                          `discount_money` int(11) DEFAULT NULL,
                          `targetRoomNum` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coupon`
--
BEGIN;
/*!40000 ALTER TABLE `Coupon` DISABLE KEYS */;
INSERT INTO `Coupon`
VALUES (2, '满500-100优惠', 2, 3, '满减优惠券',500,0,1,NULL,NULL,100,NULL),
       (3, '测试网站满减优惠，满1000减100', -1, 3, '测试网站满减', 1000, 0, 1, NULL, NULL, 100, NULL),
       (4, '测试网站限时优惠，11.1-11.11 9折', -1, 4, '测试网站限时优惠', 0, 9, 1, '2020-11-01', '2020-11-11', 0, NULL),
       (5, '测试网站限时优惠，年终巨献 85折', -1, 4, '测试网站限时优惠2', 0, 85, 1, '2020-12-15', '2020-12-31', 0, NULL);
/*!40000 ALTER TABLE `Coupon` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `hotelName` varchar(255) NOT NULL,
                         `hotelDescription` varchar(255) DEFAULT NULL,
                         `address` varchar(255) DEFAULT NULL,
                         `bizRegion` varchar(255) DEFAULT NULL,
                         `hotelStar` varchar(255) DEFAULT NULL,
                         `phoneNum` varchar(11) DEFAULT NULL,
                         `rate` double DEFAULT NULL,
                         `evaluatePeople` int DEFAULT 0,
                         `manager_id` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

BEGIN;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` VALUES
(1,'汉庭酒店','欢迎您入住','上海市虹口区四川北路1688号','XiDan','Four',18293738191,4.8,1,8),
(2,'如家酒店','欢迎您入住','南京市鼓楼区珠江路268号','XinJieKou','Three',18293738192,4.5,1,8),
(3,'维也纳酒店','欢迎您入住','南京市栖霞区珠江路153号','WanDa','Five',18295537193,4.9,1,6),
(4,'锦江之星酒店','欢迎您入住','上海市长宁区定西路1388号','WanDa','Four',18295537194,4.7,1,6),
(5,'佳驿酒店','欢迎您入住','上海市闵行区定西路七莘路3755号','XiDan','Four',18295537195,4.8,1,8),
(6,'布丁酒店','欢迎您入住','北京市朝阳区望京西路48号','WanDa','Three',18295537196,4.6,1,8),
(7,'南苑e家酒店','欢迎您入住','北京市西城区新街口南大街217号','XinJieKou','Five',18295537197,4.7,1,6),
(8,'格林豪泰酒店','欢迎您入住','南京市栖霞区仙林大道162号','WanDa','Four',18295537198,4.8,1,6),
(9,'速8酒店','欢迎您入住','北京市石景山区阜石路165号','XinJieKou','Three',18295537199,4.6,1,6);
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `OrderList`
--

DROP TABLE IF EXISTS `OrderList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderList` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `userId` int(11) DEFAULT NULL,
                             `hotelId` int(11) DEFAULT NULL,
                             `hotelName` varchar(255) DEFAULT NULL,
                             `checkInDate` varchar(255) DEFAULT NULL,
                             `checkOutDate` varchar(255) DEFAULT NULL,
                             `roomType` varchar(255) DEFAULT NULL,
                             `roomNum` int(255) DEFAULT NULL,
                             `peopleNum` int(255) DEFAULT NULL,
                             `haveChild` tinytext,
                             `createDate` varchar(255) DEFAULT NULL,
                             `price` decimal(65,0) DEFAULT NULL,
                             `clientName` varchar(255) DEFAULT NULL,
                             `phoneNumber` varchar(255) DEFAULT NULL,
                             `orderState` varchar(255) DEFAULT NULL,
                             `latestTime` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderList`
--

/*!40000 ALTER TABLE `OrderList` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderList` ENABLE KEYS */;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `price` double DEFAULT NULL,
                        `total` int(11) DEFAULT NULL,
                        `rest` int(11) DEFAULT NULL,
                        `hotel_id` int(11) DEFAULT NULL,
                        `roomType` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

BEGIN;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room`
# id, price, total, hotel_id, roomType
VALUES (2, 199, 10,10, 1, 'PresidentBed'),
       (3, 299, 30,30, 1, 'DoubleBed'),
       (4, 399, 10,10, 1, 'Family'),
       (5, 122, 10,10, 1, 'BigBed'),
       (6, 399, 10,10, 2, 'Family'),
       (7, 500, 3, 3,3, 'PresidentBed'),
       (8, 158, 30,30, 4, 'DoubleBed'),
       (9, 288, 15,15, 5, 'Family'),
       (10, 388, 20,20, 6, 'DoubleBed');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
COMMIT;

DROP TABLE IF EXISTS `Evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Evaluation` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `user_id` int(11) NOT NULL,
                              `hotel_id` int(11) NOT NULL,
                              `rate` int NOT NULL,
                              `evaluation` VARCHAR(255) NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

BEGIN;
/*!40000 ALTER TABLE `Evaluation` DISABLE KEYS */;

/*!40000 ALTER TABLE `Evaluation` DISABLE KEYS */;

COMMIT;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) DEFAULT NULL,
                        `password` varchar(11) NOT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        `phonenumber` varchar(255) DEFAULT NULL,
                        `credit` double(255,0) DEFAULT 100,
                        `usertype` varchar(255) DEFAULT NULL,
                        `memberType` varchar(255) DEFAULT '非会员',
                        `birthday` varchar(255) DEFAULT NULL,
                        `companyName` varchar(255) DEFAULT NULL,
                        `avatarUrl` varchar(255) DEFAULT 'https://orzorzorzorz.oss-cn-beijing.aliyuncs.com/UserAvatar/avatar.png',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

BEGIN;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User`
VALUES (4,'1012681@qq.com','123456','客户1号','12345678919',100,'Client', '非会员', NULL, NULL, DEFAULT),
       (5,'123@qq.com','123456','客户2号','12345678911',100,'Client','非会员', NULL, NULL, DEFAULT),
       (6,'333@qq.com','123456', '酒店工作人员1号', '18472649374', 100, 'HotelManager', '非会员', NULL, NULL, DEFAULT),
       (7, '444@qq.com', '123456', '系统管理员1号', '19956278394', 100, 'Admin', '非会员', NULL, NULL, DEFAULT),
       (8, '555@qq.com', '123456', '酒店工作人员2号', '17482946375', 100, 'HotelManager', '非会员', NULL, NULL, DEFAULT),
       (9, '666@qq.com', '123456', '网站营销人员1号', '19384736280', 100, 'Salesman', '非会员', NULL, NULL, DEFAULT);

/*!40000 ALTER TABLE `User` DISABLE KEYS */;

COMMIT;

DROP TABLE IF EXISTS `Credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Credit` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `userId` int(11) NOT NULL,
                          `action` varchar(255) DEFAULT NULL,
                          `change` double(255,0) DEFAULT 0,
                          `current` double(255,0) DEFAULT 100,
                          `time` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

BEGIN;
/*!40000 ALTER TABLE `Credit` DISABLE KEYS */;
INSERT INTO `Credit`
VALUES (1,4,'初始化',100,100,'2020-06-01 15:10:10'),
       (2,5,'初始化',100,100,'2020-06-02 12:00:00');

/*!40000 ALTER TABLE `Credit` DISABLE KEYS */;

COMMIT;

DROP TABLE IF EXISTS `oneRoom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oneRoom` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `orderId` int(11) NOT NULL,
                           `hotelId` int(11) NOT NULL ,
                           `checkInDate` datetime DEFAULT NULL,
                           `checkOutDate` datetime DEFAULT NULL,
                           `roomId` int(11) DEFAULT NULL,
                           `roomType` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actualRoom`
--

BEGIN;
/*!40000 ALTER TABLE `oneRoom` DISABLE KEYS */;


/*!40000 ALTER TABLE `oneRoom` DISABLE KEYS */;

COMMIT;




/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 10:08:42
SET FOREIGN_KEY_CHECKS = 1;