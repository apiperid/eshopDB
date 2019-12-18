-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: eshopdb
-- ------------------------------------------------------
-- Server version	5.5.57-MariaDB

DROP SCHEMA IF EXISTS `EshopDB`;
CREATE SCHEMA `EshopDB`;
USE `EshopDB`;

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
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer` (
  `ComputerID` int(7) unsigned NOT NULL,
  `RAM_ID` int(7) unsigned NOT NULL,
  `PSU_ID` int(7) unsigned NOT NULL,
  `Tower_ID` int(7) unsigned NOT NULL,
  `GPU_ID` int(7) unsigned NOT NULL,
  `MotherBoard_ID` int(7) unsigned NOT NULL,
  `CPU_ID` int(7) unsigned NOT NULL,
  `HardDisk_ID` int(7) unsigned NOT NULL,
  PRIMARY KEY (`ComputerID`),
  KEY `GPU_ID_idx` (`GPU_ID`),
  KEY `MotherBoard_ID_idx` (`MotherBoard_ID`),
  KEY `CPU_ID_idx` (`CPU_ID`),
  KEY `HardDisk_ID_idx` (`HardDisk_ID`),
  KEY `RAM_ID_idx` (`RAM_ID`),
  KEY `PSU_ID` (`PSU_ID`),
  KEY `Tower_ID` (`Tower_ID`),
  CONSTRAINT `ComputerID` FOREIGN KEY (`ComputerID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PSU_ID` FOREIGN KEY (`PSU_ID`) REFERENCES `psu` (`PSUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Tower_ID` FOREIGN KEY (`Tower_ID`) REFERENCES `tower` (`TowerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `RAM_ID` FOREIGN KEY (`RAM_ID`) REFERENCES `ram` (`RAMID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `GPU_ID` FOREIGN KEY (`GPU_ID`) REFERENCES `gpu` (`GPUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MotherBoard_ID` FOREIGN KEY (`MotherBoard_ID`) REFERENCES `motherboard` (`MotherBoardID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CPU_ID` FOREIGN KEY (`CPU_ID`) REFERENCES `cpus` (`CPUID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `HardDisk_ID` FOREIGN KEY (`HardDisk_ID`) REFERENCES `harddisk` (`HardDiskID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
INSERT INTO `computer` VALUES (49,1,41,48,25,32,9,18),(50,1,41,47,25,31,6,18),(51,2,41,48,30,33,6,18),(52,1,37,46,26,33,12,19),(53,1,39,46,27,31,13,20),(54,3,39,47,27,32,13,20);
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpus`
--

DROP TABLE IF EXISTS `cpus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cpus` (
  `CPUID` int(7) unsigned NOT NULL,
  `Clock` decimal(2,1) unsigned NOT NULL,
  `Cores` int(2) unsigned NOT NULL,
  PRIMARY KEY (`CPUID`),
  CONSTRAINT `CPUID` FOREIGN KEY (`CPUID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpus`
--

LOCK TABLES `cpus` WRITE;
/*!40000 ALTER TABLE `cpus` DISABLE KEYS */;
INSERT INTO `cpus` VALUES (6,4.0,8),(7,3.8,8),(8,2.9,2),(9,3.6,4),(10,3.4,4),(11,3.6,6),(12,4.3,8),(13,2.1,18);
/*!40000 ALTER TABLE `cpus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `cpus_for_sale`
--

DROP TABLE IF EXISTS `cpus_for_sale`;
/*!50001 DROP VIEW IF EXISTS `cpus_for_sale`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `cpus_for_sale` AS SELECT 
 1 AS `CPUID`,
 1 AS `Manufacturer`,
 1 AS `Model`,
 1 AS `Availability`,
 1 AS `Price`,
 1 AS `Clock`,
 1 AS `Cores`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `gpu`
--

DROP TABLE IF EXISTS `gpu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpu` (
  `GPUID` int(7) unsigned NOT NULL,
  `Capacity` int(4) unsigned NOT NULL,
  PRIMARY KEY (`GPUID`),
  CONSTRAINT `GPUID` FOREIGN KEY (`GPUID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpu`
--

LOCK TABLES `gpu` WRITE;
/*!40000 ALTER TABLE `gpu` DISABLE KEYS */;
INSERT INTO `gpu` VALUES (22,2),(23,2),(24,4),(25,8),(26,8),(27,8),(28,11),(29,3),(30,8);
/*!40000 ALTER TABLE `gpu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `harddisk`
--

DROP TABLE IF EXISTS `harddisk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `harddisk` (
  `HardDiskID` int(7) unsigned NOT NULL,
  `Capacity` int(4) unsigned NOT NULL,
  `Type` enum('SSD','HDD') NOT NULL,
  PRIMARY KEY (`HardDiskID`),
  CONSTRAINT `HardDiskID` FOREIGN KEY (`HardDiskID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `harddisk`
--

LOCK TABLES `harddisk` WRITE;
/*!40000 ALTER TABLE `harddisk` DISABLE KEYS */;
INSERT INTO `harddisk` VALUES (14,120,'SSD'),(15,512,'SSD'),(16,256,'SSD'),(17,256,'SSD'),(18,900,'HDD'),(19,3000,'HDD'),(20,3000,'HDD'),(21,8000,'HDD');
/*!40000 ALTER TABLE `harddisk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motherboard`
--

DROP TABLE IF EXISTS `motherboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `motherboard` (
  `MotherBoardID` int(7) unsigned NOT NULL,
  `Socket` varchar(30) NOT NULL,
  PRIMARY KEY (`MotherBoardID`),
  CONSTRAINT `MotherBoardID` FOREIGN KEY (`MotherBoardID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motherboard`
--

LOCK TABLES `motherboard` WRITE;
/*!40000 ALTER TABLE `motherboard` DISABLE KEYS */;
INSERT INTO `motherboard` VALUES (31,'SOCKET 1151'),(32,'SOCKET AM4'),(33,'SOCKET 2066'),(34,'SOCKET AM4'),(35,'SOCKET 1151'),(36,'SOCKET 1151');
/*!40000 ALTER TABLE `motherboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_contains_product`
--

DROP TABLE IF EXISTS `order_contains_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_contains_product` (
  `ProductID` int(7) unsigned NOT NULL,
  `OrderID` int(7) unsigned NOT NULL,
  `Quantity` int(2) unsigned NOT NULL,
  PRIMARY KEY (`ProductID`,`OrderID`),
  KEY `OrderID_idx` (`OrderID`),
  CONSTRAINT `ProductID` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_contains_product`
--

LOCK TABLES `order_contains_product` WRITE;
/*!40000 ALTER TABLE `order_contains_product` DISABLE KEYS */;
INSERT INTO `order_contains_product` VALUES (4,2,1),(8,1,1),(21,3,2),(23,1,1),(34,2,1),(38,4,1),(43,5,2),(49,6,1);
/*!40000 ALTER TABLE `order_contains_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `OrderID` int(7) unsigned NOT NULL,
  `TotalValue` decimal(6,2) unsigned NOT NULL,
  `Progress` enum('Initial','Waiting','Done') NOT NULL,
  `Username` varchar(15) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `Username_idx` (`Username`),
  CONSTRAINT `Username` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,279.80,'Done','papachri','2018-11-01'),(2,325.40,'Done','papachri','2018-11-02'),(3,665.00,'Waiting','user123','2018-11-21'),(4,28.50,'Waiting','user123','2018-11-21'),(5,185.80,'Initial','user123','2018-11-23'),(6,749.00,'Initial','papachri','2018-11-23');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `orders_not_finished`
--

DROP TABLE IF EXISTS `orders_not_finished`;
/*!50001 DROP VIEW IF EXISTS `orders_not_finished`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `orders_not_finished` AS SELECT 
 1 AS `OrderID`,
 1 AS `TotalValue`,
 1 AS `Progress`,
 1 AS `Username`,
 1 AS `Date`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `ProductID` int(7) unsigned NOT NULL,
  `Price` decimal(6,2) unsigned NOT NULL,
  `Availability` int(2) unsigned DEFAULT NULL,
  `Manufacturer` varchar(15) NOT NULL,
  `Model` varchar(30) NOT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,100.00,NULL,'CORSAIR','VS1GB533D2'),(2,60.00,NULL,'LENOVO','BLS2CP4G3D1609DS1S00'),(3,19.40,NULL,'KINGSTON','KVR13N9S6/2'),(4,45.50,13,'CRUCIAL','CT51264BF160B'),(5,48.90,6,'INTENSO','5631152'),(6,179.90,NULL,'AMD','SEMPRON 2650'),(7,304.90,2,'AMD','ATHLON 200GE'),(8,99.90,10,'INTEL','PENTIUM G5400'),(9,389.00,NULL,'INTEL','XEON E3-1225 V5'),(10,110.91,5,'AMD','FX-6300'),(11,165.90,2,'AMD','ATHLON X4 880K'),(12,439.00,NULL,'AMD','FX-8320'),(13,2977.72,NULL,'INTEL','XEON E5-2695V4'),(14,22.90,5,'PATRIOT','PBU120GS25SSDR BURST'),(15,73.90,10,'INTENSO','3813430'),(16,55.90,3,'ADATA','PREMIER PRO SP600'),(17,56.00,8,'PLEXTOR','PX-128S3G'),(18,299.00,NULL,'SEAGATE','ST1000DM010 BARRACUDA'),(19,77.90,NULL,'TOSHIBA','DT01ACA050'),(20,97.90,NULL,'WESTERN DIGITAL','WDS120G2G0A'),(21,332.50,2,'SEAGATE','ST500LM030 BARRACUDA'),(22,140.90,10,'GIGABYTE','RADEON RX550'),(23,179.90,5,'GIGABYTE','GEFORCE GT1030'),(24,179.90,14,'ASUS','GEFORCE GTX1060'),(25,419.00,NULL,'ASUS','RADEON RX580'),(26,1429.78,NULL,'SAPPHIRE','PULSE RADEON RX570 ITX'),(27,599.00,NULL,'NVIDIA','GEFORCE 210 GV-N210D3-1GI'),(28,699.00,3,'NVIDIA','GEFORCE GT710 VG-N710D3-1GL'),(29,203.54,20,'PALIT','GEFORCE GTX1060'),(30,784.28,NULL,'PALIT','GEFORCE GTX1070'),(31,339.00,NULL,'GIGABYTE','GA-H110M-S2V'),(32,273.69,NULL,'GIGABYTE','GA-H110M-S2'),(33,489.00,NULL,'ASROCK','FM2A68M-DG3+'),(34,279.90,5,'ASROCK','H81M-VG4 R3.0'),(35,79.99,12,'ASUS','H110M-C'),(36,165.90,18,'ASUS','PRIME B250M-K'),(37,42.90,NULL,'CORSAIR','VS SERIES VS350'),(38,28.50,10,'NOD','PSU-104 ATX BLACK'),(39,22.90,NULL,'GEMBIRD','CCC-PSU4X ATX/BTX'),(40,33.90,3,'COOLERMASTER','ELITE V3'),(41,34.90,NULL,'FORCE','FO25XD'),(42,35.90,10,'INNOVATOR','IN06XD'),(43,92.90,3,'BE QUIET','PURE BASE 600 BLACK'),(44,125.90,12,'BE QUIET','SILENT BASE 600 BLACK'),(45,39.90,9,'COOLERMASTER','MASTERBOX E300L RED'),(46,72.90,NULL,'CORSAIR','CARBIDE SERIES SPEC-02'),(47,38.50,NULL,'DEEPCOOL','D-SHIELD V2'),(48,34.90,NULL,'NATEC','NPC-1291 BOLITA'),(49,749.00,5,'INNOVATOR ','INNOVATOR 3'),(50,847.80,10,'INNOVATOR ','INNOVATOR 3 CYBER GAMER 7500'),(51,950.40,13,'KaizerStore','KaizerStore Desktop PC K5'),(52,1100.60,9,'INFO PC GAMING','INFO PC GAMING RYZEN 5'),(53,4500.70,17,'EXPERT PC','EXPERT PC RYZEN 5'),(54,4000.35,7,'Msystems','Msystems PC PUBG Ready');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `psu`
--

DROP TABLE IF EXISTS `psu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `psu` (
  `PSUID` int(7) unsigned NOT NULL,
  `Watts` int(3) unsigned NOT NULL,
  PRIMARY KEY (`PSUID`),
  CONSTRAINT `PSUID` FOREIGN KEY (`PSUID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `psu`
--

LOCK TABLES `psu` WRITE;
/*!40000 ALTER TABLE `psu` DISABLE KEYS */;
INSERT INTO `psu` VALUES (37,350),(38,450),(39,400),(40,300),(41,750),(42,500);
/*!40000 ALTER TABLE `psu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ram`
--

DROP TABLE IF EXISTS `ram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ram` (
  `RAMID` int(7) unsigned NOT NULL,
  `Capacity` int(4) unsigned NOT NULL,
  PRIMARY KEY (`RAMID`),
  CONSTRAINT `RAMID` FOREIGN KEY (`RAMID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ram`
--

LOCK TABLES `ram` WRITE;
/*!40000 ALTER TABLE `ram` DISABLE KEYS */;
INSERT INTO `ram` VALUES (1,8),(2,8),(3,2),(4,4),(5,8);
/*!40000 ALTER TABLE `ram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `rams_not_attached`
--

DROP TABLE IF EXISTS `rams_not_attached`;
/*!50001 DROP VIEW IF EXISTS `rams_not_attached`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `rams_not_attached` AS SELECT 
 1 AS `RAMID`,
 1 AS `Capacity`,
 1 AS `Manufacturer`,
 1 AS `Model`,
 1 AS `Price`,
 1 AS `Availability`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tower`
--

DROP TABLE IF EXISTS `tower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tower` (
  `TowerID` int(7) unsigned NOT NULL,
  `Dimensions` varchar(30) NOT NULL,
  PRIMARY KEY (`TowerID`),
  CONSTRAINT `TowerID` FOREIGN KEY (`TowerID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tower`
--

LOCK TABLES `tower` WRITE;
/*!40000 ALTER TABLE `tower` DISABLE KEYS */;
INSERT INTO `tower` VALUES (43,'492x220x470 '),(44,'495x230x493 '),(45,'448x180x364 '),(46,'493x215x426 '),(47,'438x210x477 '),(48,'365x180x410');
/*!40000 ALTER TABLE `tower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(15) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Phone` char(10) NOT NULL,
  `BirthDate` date NOT NULL,
  `IBAN` varchar(30) NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Street` varchar(30) NOT NULL,
  `City` varchar(12) NOT NULL,
  `PostalCode` int(5) unsigned NOT NULL,
  `FirstName` varchar(15) NOT NULL,
  `LastName` varchar(15) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('apiperid','123456789','email123@gmail.com','6948352176','1996-11-12','GR1601101250000000012300675 ','Male','Venizelou 80','Veroia',54349,'Giannis','Papadopoulos'),('knikolla','qwerty_@','email890@gmail.com','6989603420','1990-10-01','GR1601101274900000012300675','Male','Agiou Dimitriou 34','Kilkis',61100,'George','Georgiadis'),('papachri','987654321','user124@yahoo.gr','6942347678','1980-01-06','GR1601101250000000856470675','Male','Markou Mpotsari 90','Athens',58320,'Kostas','Dimitriou'),('user123','dgsdfssfsad','ffsd7979@gmail.com','6942656427','1994-02-09','GR1601101250000000856478492','Female','Megalou Alexandrou 2','Larissa',54445,'Mary','Papadopoulou'),('user2','539t8gufjhs','dsajl324@yahoo.gr','6990249732','1990-06-29','GR1601101250095642856478492','Male','Olympiados 12','Arta',78924,'Petros','Avraamidis'),('user3','fhskdf3428','slji32@gmail.com','6934572390','1960-08-28','GR1601101250090987654300675','Male','Egnatias 123','Lamia',59100,'Thanos','Aggelidis');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `cpus_for_sale`
--

/*!50001 DROP VIEW IF EXISTS `cpus_for_sale`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `cpus_for_sale` AS select `cpus`.`CPUID` AS `CPUID`,`product`.`Manufacturer` AS `Manufacturer`,`product`.`Model` AS `Model`,`product`.`Availability` AS `Availability`,`product`.`Price` AS `Price`,`cpus`.`Clock` AS `Clock`,`cpus`.`Cores` AS `Cores` from (`cpus` join `product` on((`cpus`.`CPUID` = `product`.`ProductID`))) where ((`product`.`Availability` is not null) and (`product`.`Availability` > 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `orders_not_finished`
--

/*!50001 DROP VIEW IF EXISTS `orders_not_finished`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `orders_not_finished` AS select `orders`.`OrderID` AS `OrderID`,`orders`.`TotalValue` AS `TotalValue`,`orders`.`Progress` AS `Progress`,`orders`.`Username` AS `Username`,`orders`.`Date` AS `Date` from `orders` where (`orders`.`Progress` <> 'Done') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rams_not_attached`
--

/*!50001 DROP VIEW IF EXISTS `rams_not_attached`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rams_not_attached` AS select `ram`.`RAMID` AS `RAMID`,`ram`.`Capacity` AS `Capacity`,`product`.`Manufacturer` AS `Manufacturer`,`product`.`Model` AS `Model`,`product`.`Price` AS `Price`,`product`.`Availability` AS `Availability` from (`ram` join `product` on((`ram`.`RAMID` = `product`.`ProductID`))) where (`product`.`Availability` is not null) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-22 19:16:04
