-- MySQL dump 10.16  Distrib 10.1.13-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: hotelfx
-- ------------------------------------------------------
-- Server version	10.4.10-MariaDB

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
-- Current Database: `hotelfx`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `hotelfx` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `hotelfx`;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookings` (
  `bookingID` int(11) NOT NULL AUTO_INCREMENT,
  `dateFrom` timestamp NULL DEFAULT NULL,
  `dateTo` timestamp NULL DEFAULT NULL,
  `roomCount` varchar(45) NOT NULL,
  `fk_guestID` int(11) NOT NULL,
  `fk_reservationAgentID` int(11) NOT NULL,
  `fk_bookingStatusID` int(11) NOT NULL,
  `fk_hotelID` int(11) NOT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `fk_bookings_guests_idx` (`fk_guestID`),
  KEY `fk_bookings_reservationAgents_idx` (`fk_reservationAgentID`),
  KEY `fk_bookings_bookingstatus1_idx` (`fk_bookingStatusID`),
  KEY `fk_bookings_hotels1_idx` (`fk_hotelID`),
  CONSTRAINT `fk_bookings_bookingstatus` FOREIGN KEY (`fk_bookingStatusID`) REFERENCES `bookingstatus` (`bookingStatusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bookings_guests` FOREIGN KEY (`fk_guestID`) REFERENCES `guests` (`guestID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bookings_hotels1` FOREIGN KEY (`fk_hotelID`) REFERENCES `hotels` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bookings_reservationAgents` FOREIGN KEY (`fk_reservationAgentID`) REFERENCES `reservationagents` (`reservationAgentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'2019-12-31 23:00:00','2020-01-10 23:00:00','1',1,4,2,1),(2,'2020-02-22 23:00:00','2020-02-18 23:00:00','1',2,1,1,1),(3,'2020-02-24 23:00:00','0000-00-00 00:00:00','1',3,2,1,2),(4,'2020-02-29 23:00:00','2020-03-31 22:00:00','2',4,2,1,2),(5,'2020-01-23 23:00:00','2020-01-25 23:00:00','3',1,1,1,1),(6,'2020-01-23 23:00:00','2020-02-06 23:00:00','2',3,1,1,1),(7,'2020-01-22 23:00:00','2020-01-25 23:00:00','1',1,1,1,1),(8,'2020-01-23 23:00:00','2020-01-25 23:00:00','1',1,1,1,1),(9,'2020-01-22 23:00:00','2020-01-25 23:00:00','1',1,1,1,1),(10,'2020-01-24 23:00:00','2020-01-30 23:00:00','1',1,1,1,1),(11,'2020-02-06 23:00:00','2020-02-08 23:00:00','1',1,1,1,1);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookingstatus`
--

DROP TABLE IF EXISTS `bookingstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookingstatus` (
  `bookingStatusID` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  `description` varchar(500) NOT NULL,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`bookingStatusID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingstatus`
--

LOCK TABLES `bookingstatus` WRITE;
/*!40000 ALTER TABLE `bookingstatus` DISABLE KEYS */;
INSERT INTO `bookingstatus` VALUES (1,'ACTIVE','guest has ordered a room',1),(2,'EXPIRED','guest has checked out',1);
/*!40000 ALTER TABLE `bookingstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guests`
--

DROP TABLE IF EXISTS `guests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guests` (
  `guestID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zipCode` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  PRIMARY KEY (`guestID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='																	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guests`
--

LOCK TABLES `guests` WRITE;
/*!40000 ALTER TABLE `guests` DISABLE KEYS */;
INSERT INTO `guests` VALUES (1,'Alfons','Mob','Rudolfshügel 57 / 23b','Wien','Wien','1160','AUT','+43 83 428 12','rmob@bash.at','M'),(2,'Bonny','Rusk','Upsideroad','New York','New York','49120','US','+01 349 349 21','bonnyrusk@mail.com','F'),(3,'Mandy','Thompson','Westington Road 58 / 23b','London','London','34990','EN','+23 234 432 12','mthompson@mandy.net','F'),(4,'Ruthgard','Zapp','Wellington Road','Sprigfield','Texas','67900','US','+01 333 830 02','rzapp@blond.com','M'),(5,'sdds','sd','sdsd','sds','sdsdsd','ds','dsd','sd','sdsd','sd'),(6,'cx','g','g','g','cx','g','g','g','g','g'),(7,'d','d','d','d','d','d','d','d','d','d'),(8,'s','s','s','s','s','s','s','s','s','s'),(9,'s','s','s','s','s','s','s','s','s','s'),(10,'12','12','h','h','12','h','h','h','h','h');
/*!40000 ALTER TABLE `guests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotels`
--

DROP TABLE IF EXISTS `hotels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotels` (
  `hotelID` int(11) NOT NULL AUTO_INCREMENT,
  `hotelCode` varchar(45) NOT NULL,
  `hotelName` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zipCode` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `hotelPhoneNumber` varchar(45) NOT NULL,
  `hotelEmailAddress` varchar(45) NOT NULL,
  `hotelWebAddress` varchar(45) NOT NULL,
  PRIMARY KEY (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='																	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotels`
--

LOCK TABLES `hotels` WRITE;
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
INSERT INTO `hotels` VALUES (1,'001','Five Seasons','Bamberger Straße 21/7','Berlin','Berlin','45100','GER','+49 / 34 465 77','fifeseasons@hotel.de','fifeseasons.de'),(2,'002','Four Seasons','Vokuhila Straße 44 / 23a','München','Bayern','21300','GER','+49 / 12 900 34','fourseasons@hotel.de','fourseasons.de');
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `paymentID` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `payment` varchar(45) NOT NULL,
  `fk_roomID` int(11) NOT NULL,
  `fk_paymentTypeID` int(11) NOT NULL,
  `fk_paymentStatusID` int(11) NOT NULL,
  PRIMARY KEY (`paymentID`),
  KEY `fk_payments_rooms1_idx` (`fk_roomID`),
  KEY `fk_payments_paymentStatus1_idx` (`fk_paymentTypeID`),
  KEY `fk_payments_paymentStatus2_idx` (`fk_paymentStatusID`),
  CONSTRAINT `fk_payments_paymentStatus1` FOREIGN KEY (`fk_paymentTypeID`) REFERENCES `paymenttype` (`paymentTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_paymentStatus2` FOREIGN KEY (`fk_paymentStatusID`) REFERENCES `paymentstatus` (`paymentStatusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_rooms1` FOREIGN KEY (`fk_roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,'2020-01-23 10:13:22','',6,2,1),(2,'2020-01-23 10:15:09','',3,1,1),(3,'2020-01-23 10:15:19','',23,3,1),(4,'2020-01-23 10:15:22','',24,3,1),(5,'2020-01-23 10:15:25','',25,2,1),(6,'2020-01-23 10:36:55','120',1,1,1),(7,'2020-01-23 11:38:25','240',1,1,1),(8,'2020-01-23 11:39:19','80',1,1,1);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentstatus`
--

DROP TABLE IF EXISTS `paymentstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentstatus` (
  `paymentStatusID` int(11) NOT NULL AUTO_INCREMENT,
  `paymentStatus` tinyint(4) NOT NULL,
  `paymentStatusDescription` varchar(45) NOT NULL,
  PRIMARY KEY (`paymentStatusID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentstatus`
--

LOCK TABLES `paymentstatus` WRITE;
/*!40000 ALTER TABLE `paymentstatus` DISABLE KEYS */;
INSERT INTO `paymentstatus` VALUES (1,0,'fully paid'),(2,0,'peniding transaction conformation'),(3,0,'unpaid bills'),(4,0,'bank transaction error');
/*!40000 ALTER TABLE `paymentstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymenttype`
--

DROP TABLE IF EXISTS `paymenttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymenttype` (
  `paymentTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `paymentType` varchar(45) NOT NULL,
  PRIMARY KEY (`paymentTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymenttype`
--

LOCK TABLES `paymenttype` WRITE;
/*!40000 ALTER TABLE `paymenttype` DISABLE KEYS */;
INSERT INTO `paymenttype` VALUES (1,'CASH'),(2,'PAYPAL'),(3,'BTRF');
/*!40000 ALTER TABLE `paymenttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rates` (
  `rateID` int(11) NOT NULL AUTO_INCREMENT,
  `rate` varchar(45) NOT NULL,
  `rateFrom` timestamp NULL DEFAULT NULL,
  `rateTo` timestamp NULL DEFAULT NULL,
  `fk_roomID` int(11) NOT NULL,
  `rateTypes_rateTypeID` int(11) NOT NULL,
  PRIMARY KEY (`rateID`),
  KEY `fk_rates_rooms1_idx` (`fk_roomID`),
  KEY `fk_rates_rateTypes1_idx` (`rateTypes_rateTypeID`),
  CONSTRAINT `fk_rates_rateTypes1` FOREIGN KEY (`rateTypes_rateTypeID`) REFERENCES `ratetypes` (`rateTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rates_rooms1` FOREIGN KEY (`fk_roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratetypes`
--

DROP TABLE IF EXISTS `ratetypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratetypes` (
  `rateTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `rateType` varchar(45) NOT NULL,
  `rateDescription` varchar(45) NOT NULL,
  PRIMARY KEY (`rateTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratetypes`
--

LOCK TABLES `ratetypes` WRITE;
/*!40000 ALTER TABLE `ratetypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratetypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationagents`
--

DROP TABLE IF EXISTS `reservationagents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservationagents` (
  `reservationAgentID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zipCode` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  PRIMARY KEY (`reservationAgentID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationagents`
--

LOCK TABLES `reservationagents` WRITE;
/*!40000 ALTER TABLE `reservationagents` DISABLE KEYS */;
INSERT INTO `reservationagents` VALUES (1,'Agent1','Agent1NN','ag1add','Webroad','Berlin','34000','GER','+49 234 123 32','ag1@der.de','M'),(2,'Agent2','Agent2NN','ag2add','Blubstreet','Wien','1200','AUT','+43 349 12 34','ag2@der.at','M'),(3,'Agent3','Agent3NN','ag2add','Bubbleroad','St. Pölten','2340','AUT','+43 002 43 12','ag3@der.at','F'),(4,'Agent4','Agent4NN','ag4add','Vennstreet','Wien','1100','AUT','+43 495 12 00','ag4@der.at','M');
/*!40000 ALTER TABLE `reservationagents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `roomID` int(11) NOT NULL AUTO_INCREMENT,
  `floor` int(11) NOT NULL,
  `roomNumber` varchar(45) NOT NULL,
  `description` varchar(500) NOT NULL,
  `fk_hotelID` int(11) NOT NULL,
  `fk_roomStatusID` int(11) NOT NULL,
  `fk_roomTypeID` int(11) NOT NULL,
  PRIMARY KEY (`roomID`),
  KEY `fk_rooms_hotels1_idx` (`fk_hotelID`),
  KEY `fk_rooms_roomStatus1_idx` (`fk_roomStatusID`),
  KEY `fk_rooms_roomType1_idx` (`fk_roomTypeID`),
  CONSTRAINT `fk_rooms_hotels1` FOREIGN KEY (`fk_hotelID`) REFERENCES `hotels` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rooms_roomStatus1` FOREIGN KEY (`fk_roomStatusID`) REFERENCES `roomstatus` (`roomStatusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rooms_roomType1` FOREIGN KEY (`fk_roomTypeID`) REFERENCES `roomtype` (`roomTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,1,'001','-',1,1,4),(2,1,'002','-',1,1,4),(3,1,'003','-',1,2,4),(4,1,'004','-',1,1,4),(5,1,'005','-',1,1,4),(6,2,'006','-',1,1,4),(7,2,'007','-',1,1,4),(8,2,'008','-',1,1,4),(9,2,'009','-',1,1,4),(10,2,'010','-',1,1,4),(11,3,'011','-',1,1,3),(12,3,'012','-',1,1,3),(13,3,'013','-',1,1,3),(14,3,'014','-',1,1,3),(15,3,'015','-',1,1,3),(16,4,'016','-',1,3,2),(17,4,'017','-',1,1,2),(18,4,'018','-',1,3,2),(19,4,'019','-',1,3,2),(20,4,'020','-',1,3,1),(21,1,'001','-',2,1,4),(22,1,'002','-',2,1,4),(23,1,'003','-',2,2,4),(24,1,'004','-',2,2,3),(25,1,'005','-',2,2,3),(26,2,'006','-',2,1,3),(27,2,'007','-',2,1,2),(28,2,'008','-',2,1,2),(29,2,'009','-',2,1,2),(30,2,'010','-',2,1,2),(31,3,'011','-',2,3,2),(32,3,'012','-',2,3,2),(33,3,'013','-',2,3,2),(34,3,'014','-',2,3,2),(35,3,'015','-',2,3,1),(36,4,'016','-',2,3,2),(37,4,'017','-',2,3,2),(38,4,'018','-',2,3,1),(39,4,'019','-',2,3,1),(40,4,'020','-',2,3,1),(41,1,'S01','-',1,1,4),(42,0,'S02','-',1,1,4),(43,1,'S01','-',2,1,4),(44,0,'S02','-',2,1,4);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomsbooked`
--

DROP TABLE IF EXISTS `roomsbooked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomsbooked` (
  `roomsBookedID` int(11) NOT NULL AUTO_INCREMENT,
  `roomID` int(11) NOT NULL,
  `fk_bookingID` int(11) NOT NULL,
  PRIMARY KEY (`roomsBookedID`,`roomID`),
  KEY `fk_roomsBooked_rooms1_idx` (`roomID`),
  KEY `fk_roomsBooked_bookings1_idx` (`fk_bookingID`),
  CONSTRAINT `fk_roomsBooked_bookings` FOREIGN KEY (`fk_bookingID`) REFERENCES `bookings` (`bookingID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roomsBooked_rooms` FOREIGN KEY (`roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomsbooked`
--

LOCK TABLES `roomsbooked` WRITE;
/*!40000 ALTER TABLE `roomsbooked` DISABLE KEYS */;
INSERT INTO `roomsbooked` VALUES (1,6,1),(2,3,2),(3,23,3),(4,24,4),(5,25,4),(6,1,5),(7,15,5),(8,17,5),(9,13,6),(10,12,6),(11,1,7),(12,1,8),(13,1,9),(14,1,10),(15,1,11);
/*!40000 ALTER TABLE `roomsbooked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomstatus`
--

DROP TABLE IF EXISTS `roomstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomstatus` (
  `roomStatusID` int(11) NOT NULL AUTO_INCREMENT,
  `roomStatus` varchar(45) NOT NULL,
  `roomStatusDescription` varchar(45) NOT NULL,
  `roomStatusActive` tinyint(4) NOT NULL,
  PRIMARY KEY (`roomStatusID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomstatus`
--

LOCK TABLES `roomstatus` WRITE;
/*!40000 ALTER TABLE `roomstatus` DISABLE KEYS */;
INSERT INTO `roomstatus` VALUES (1,'FREE','cleaned and ready to go',1),(2,'OCCUPIED','guest is in the rooms',1),(3,'IN_SERVICE','cleaning in progress',1);
/*!40000 ALTER TABLE `roomstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomtype` (
  `roomTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `roomType` varchar(45) NOT NULL,
  `roomTypeDescription` int(11) NOT NULL,
  `roomTypeActive` tinyint(4) NOT NULL,
  PRIMARY KEY (`roomTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
INSERT INTO `roomtype` VALUES (1,'PRIME',100,1),(2,'COMFORT',80,1),(3,'STANDARD',60,1),(4,'BASIC',40,1);
/*!40000 ALTER TABLE `roomtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staffID` int(11) NOT NULL AUTO_INCREMENT,
  `staffPositionID` varchar(45) NOT NULL,
  `staffFirstName` varchar(45) NOT NULL,
  `staffLastName` varchar(45) NOT NULL,
  `staffAddress` varchar(45) NOT NULL,
  `staffCity` varchar(45) NOT NULL,
  `staffState` varchar(45) NOT NULL,
  `staffZipCode` varchar(45) NOT NULL,
  `staffCountry` varchar(45) NOT NULL,
  `staffPhoneNumber` varchar(45) NOT NULL,
  `staffEmailAddress` varchar(45) NOT NULL,
  PRIMARY KEY (`staffID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='																	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'0001','Hugo','Boss','BossRoad','BossCity','Berlin','11000','GER','+49 324 83 84','boss@hotel.de'),(2,'0002','Billy','Receptionist','ReceptionRoad','ReceptionCity','Berlin','11000','GER','+49 348 11 11','receptionist@hotelde'),(3,'0001','Andrew','Boss','BossRoad','Wien','Wien','1100','AUT','+43 149 34 32','boss2@hotel.at'),(4,'0002','Mark','Receptionist','ReceptionRoad','Wien','Wien','1100','AUT','+43 599 09 34','receptionist2@hotel.at');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staffrooms`
--

DROP TABLE IF EXISTS `staffrooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staffrooms` (
  `staffRoomsID` int(11) NOT NULL,
  `staffRooms` varchar(45) NOT NULL,
  `fk_staffID` int(11) NOT NULL,
  `fk_roomID` int(11) NOT NULL,
  PRIMARY KEY (`staffRoomsID`),
  KEY `fk_staffRooms_staff1_idx` (`fk_staffID`),
  KEY `fk_staffRooms_rooms1_idx` (`fk_roomID`),
  CONSTRAINT `fk_staffRooms_rooms1` FOREIGN KEY (`fk_roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_staffRooms_staff1` FOREIGN KEY (`fk_staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staffrooms`
--

LOCK TABLES `staffrooms` WRITE;
/*!40000 ALTER TABLE `staffrooms` DISABLE KEYS */;
INSERT INTO `staffrooms` VALUES (1,'Office',1,41),(2,'Office',3,43),(3,'receptionRoom',2,42),(4,'receptionRoom',4,44);
/*!40000 ALTER TABLE `staffrooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-23 14:00:48
