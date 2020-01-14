-- MariaDB dump 10.17  Distrib 10.4.10-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: hotelfx
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='																	';
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='																	';
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `paymentscol` varchar(45) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomtype` (
  `roomTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `roomType` varchar(45) NOT NULL,
  `roomTypeDescription` varchar(45) NOT NULL,
  `roomTypeActive` tinyint(4) NOT NULL,
  PRIMARY KEY (`roomTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='																	';
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-14 16:50:39
