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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'2019-12-31 23:00:00','2020-01-10 23:00:00','1',1,4,2,1),(2,'2020-02-22 23:00:00','2020-02-18 23:00:00','1',2,1,1,1),(3,'2020-02-24 23:00:00','0000-00-00 00:00:00','1',3,2,1,2),(4,'2020-02-29 23:00:00','2020-03-31 22:00:00','2',4,2,1,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,'2020-01-16 21:22:36','PAYPAL',6,2,1),(2,'0000-00-00 00:00:00','CASH',3,1,1),(3,'0000-00-00 00:00:00','BTRF',23,3,1),(4,'0000-00-00 00:00:00','BTRF',24,3,1),(5,'2020-01-16 21:23:07','PAYPAL',25,2,1);
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
