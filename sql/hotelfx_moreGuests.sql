-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 24. Jan 2020 um 01:46
-- Server-Version: 10.4.10-MariaDB
-- PHP-Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `hotelfx`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bookings`
--

CREATE TABLE `bookings` (
  `bookingID` int(11) NOT NULL,
  `dateFrom` timestamp NULL DEFAULT NULL,
  `dateTo` timestamp NULL DEFAULT NULL,
  `roomCount` varchar(45) NOT NULL,
  `fk_guestID` int(11) NOT NULL,
  `fk_reservationAgentID` int(11) NOT NULL,
  `fk_bookingStatusID` int(11) NOT NULL,
  `fk_hotelID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bookingstatus`
--

CREATE TABLE `bookingstatus` (
  `bookingStatusID` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `description` varchar(500) NOT NULL,
  `active` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `bookingstatus`
--

INSERT INTO `bookingstatus` (`bookingStatusID`, `status`, `description`, `active`) VALUES
(1, 'ACTIVE', 'guest has ordered a room', 1),
(2, 'EXPIRED', 'guest has checked out', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `guests`
--

CREATE TABLE `guests` (
  `guestID` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zipCode` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='																	';

--
-- Daten für Tabelle `guests`
--

INSERT INTO `guests` (`guestID`, `firstName`, `lastName`, `address`, `city`, `state`, `zipCode`, `country`, `phoneNumber`, `emailAddress`, `gender`) VALUES
(1, 'Alfons', 'Mob', 'Rudolfshügel 57 / 23b', 'Wien', 'Wien', '1160', 'AUT', '+43 83 428 12', 'rmob@bash.at', 'M'),
(2, 'Bonny', 'Rusk', 'Upsideroad', 'New York', 'New York', '49120', 'US', '+01 349 349 21', 'bonnyrusk@mail.com', 'F'),
(3, 'Mandy', 'Thompson', 'Westington Road 58 / 23b', 'London', 'London', '34990', 'EN', '+23 234 432 12', 'mthompson@mandy.net', 'F'),
(4, 'Ruthgard', 'Zapp', 'Wellington Road', 'Sprigfield', 'Texas', '67900', 'US', '+01 333 830 02', 'rzapp@blond.com', 'M'),
(5, 'Mark', 'Batra', 'Obere Straße 12', 'Wien', 'Wien', '1220', 'AUT', '+01 223 452 12', 'mBatra@bash.at', 'M'),
(6, 'Klemenz', 'Josir', 'Am Ring 1', 'Wien', 'Wien', '1010', 'AUT', '+0663 030 040 02', 'kJosira@bash.at', 'M'),
(7, 'Magdalena', 'Wurfer', 'Linke Straße 10', 'Wien', 'Wien', '1100', 'AUT', '+01 223 002 23', 'mWurfer@bash.at', 'F'),
(8, 'Katharina', 'Mast', 'Dorfstraße 114', 'Salzburg', 'Salzburg', '2345', 'AUT', '+43 999 223 00', 'kMast@bash.at', 'F'),
(9, 'Leo', 'Koll', 'Lasknergasse 23', 'Linz', 'Oberösterreich', '10212', 'AUT', '+43 923 923 02', 'lKoll@bash.at', 'M'),
(10, 'Maria', 'Anler', 'Fischgasse 13', 'Wien', 'Wien', '1020', 'AUT', '0660 222 222 22', 'mAnler@bash.at', 'F'),
(11, 'Ted', 'Garp', 'Spring Road 22', 'London', 'London', '35555', 'EN', '+23 222 830 02', 'tGarp@blond.com', 'M'),
(12, 'Fred', 'Glocke', 'Kurze Straße 45', 'Salzburg', 'Salzburg', '34002', 'AUT', '+43 340 000 99', 'fGlocke@bash.at', 'M');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hotels`
--

CREATE TABLE `hotels` (
  `hotelID` int(11) NOT NULL,
  `hotelCode` varchar(45) NOT NULL,
  `hotelName` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zipCode` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `hotelPhoneNumber` varchar(45) NOT NULL,
  `hotelEmailAddress` varchar(45) NOT NULL,
  `hotelWebAddress` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='																	';

--
-- Daten für Tabelle `hotels`
--

INSERT INTO `hotels` (`hotelID`, `hotelCode`, `hotelName`, `address`, `city`, `state`, `zipCode`, `country`, `hotelPhoneNumber`, `hotelEmailAddress`, `hotelWebAddress`) VALUES
(1, '001', 'Five Seasons', 'Bamberger Straße 21/7', 'Berlin', 'Berlin', '45100', 'GER', '+49 / 34 465 77', 'fifeseasons@hotel.de', 'fifeseasons.de'),
(2, '002', 'Four Seasons', 'Vokuhila Straße 44 / 23a', 'München', 'Bayern', '21300', 'GER', '+49 / 12 900 34', 'fourseasons@hotel.de', 'fourseasons.de');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `payments`
--

CREATE TABLE `payments` (
  `paymentID` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `payment` varchar(45) NOT NULL,
  `fk_roomID` int(11) NOT NULL,
  `fk_paymentTypeID` int(11) NOT NULL,
  `fk_paymentStatusID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `paymentstatus`
--

CREATE TABLE `paymentstatus` (
  `paymentStatusID` int(11) NOT NULL,
  `paymentStatus` tinyint(4) NOT NULL,
  `paymentStatusDescription` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `paymentstatus`
--

INSERT INTO `paymentstatus` (`paymentStatusID`, `paymentStatus`, `paymentStatusDescription`) VALUES
(1, 0, 'fully paid'),
(2, 0, 'peniding transaction conformation'),
(3, 0, 'unpaid bills'),
(4, 0, 'bank transaction error');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `paymenttype`
--

CREATE TABLE `paymenttype` (
  `paymentTypeID` int(11) NOT NULL,
  `paymentType` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `paymenttype`
--

INSERT INTO `paymenttype` (`paymentTypeID`, `paymentType`) VALUES
(1, 'CASH'),
(2, 'PAYPAL'),
(3, 'BTRF');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rates`
--

CREATE TABLE `rates` (
  `rateID` int(11) NOT NULL,
  `rate` varchar(45) NOT NULL,
  `rateFrom` timestamp NULL DEFAULT NULL,
  `rateTo` timestamp NULL DEFAULT NULL,
  `fk_roomID` int(11) NOT NULL,
  `rateTypes_rateTypeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ratetypes`
--

CREATE TABLE `ratetypes` (
  `rateTypeID` int(11) NOT NULL,
  `rateType` varchar(45) NOT NULL,
  `rateDescription` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservationagents`
--

CREATE TABLE `reservationagents` (
  `reservationAgentID` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `zipCode` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `reservationagents`
--

INSERT INTO `reservationagents` (`reservationAgentID`, `firstName`, `lastName`, `address`, `city`, `state`, `zipCode`, `country`, `phoneNumber`, `emailAddress`, `gender`) VALUES
(1, 'Agent1', 'Agent1NN', 'ag1add', 'Webroad', 'Berlin', '34000', 'GER', '+49 234 123 32', 'ag1@der.de', 'M'),
(2, 'Agent2', 'Agent2NN', 'ag2add', 'Blubstreet', 'Wien', '1200', 'AUT', '+43 349 12 34', 'ag2@der.at', 'M'),
(3, 'Agent3', 'Agent3NN', 'ag2add', 'Bubbleroad', 'St. Pölten', '2340', 'AUT', '+43 002 43 12', 'ag3@der.at', 'F'),
(4, 'Agent4', 'Agent4NN', 'ag4add', 'Vennstreet', 'Wien', '1100', 'AUT', '+43 495 12 00', 'ag4@der.at', 'M');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rooms`
--

CREATE TABLE `rooms` (
  `roomID` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `roomNumber` varchar(45) NOT NULL,
  `description` varchar(500) NOT NULL,
  `fk_hotelID` int(11) NOT NULL,
  `fk_roomStatusID` int(11) NOT NULL,
  `fk_roomTypeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `rooms`
--

INSERT INTO `rooms` (`roomID`, `floor`, `roomNumber`, `description`, `fk_hotelID`, `fk_roomStatusID`, `fk_roomTypeID`) VALUES
(1, 1, '001', '-', 1, 1, 4),
(2, 1, '002', '-', 1, 1, 4),
(3, 1, '003', '-', 1, 1, 4),
(4, 1, '004', '-', 1, 1, 4),
(5, 1, '005', '-', 1, 1, 4),
(6, 2, '006', '-', 1, 1, 4),
(7, 2, '007', '-', 1, 1, 4),
(8, 2, '008', '-', 1, 1, 4),
(9, 2, '009', '-', 1, 1, 4),
(10, 2, '010', '-', 1, 1, 4),
(11, 3, '011', '-', 1, 1, 3),
(12, 3, '012', '-', 1, 1, 3),
(13, 3, '013', '-', 1, 1, 3),
(14, 3, '014', '-', 1, 1, 3),
(15, 3, '015', '-', 1, 1, 3),
(16, 4, '016', '-', 1, 3, 2),
(17, 4, '017', '-', 1, 1, 2),
(18, 4, '018', '-', 1, 3, 2),
(19, 4, '019', '-', 1, 3, 2),
(20, 4, '020', '-', 1, 3, 1),
(21, 1, '001', '-', 2, 1, 4),
(22, 1, '002', '-', 2, 1, 4),
(23, 1, '003', '-', 2, 1, 4),
(24, 1, '004', '-', 2, 1, 3),
(25, 1, '005', '-', 2, 1, 3),
(26, 2, '006', '-', 2, 1, 3),
(27, 2, '007', '-', 2, 1, 2),
(28, 2, '008', '-', 2, 1, 2),
(29, 2, '009', '-', 2, 1, 2),
(30, 2, '010', '-', 2, 1, 2),
(31, 3, '011', '-', 2, 3, 2),
(32, 3, '012', '-', 2, 3, 2),
(33, 3, '013', '-', 2, 3, 2),
(34, 3, '014', '-', 2, 3, 2),
(35, 3, '015', '-', 2, 3, 1),
(36, 4, '016', '-', 2, 3, 2),
(37, 4, '017', '-', 2, 3, 2),
(38, 4, '018', '-', 2, 3, 1),
(39, 4, '019', '-', 2, 3, 1),
(40, 4, '020', '-', 2, 3, 1),
(41, 1, 'S01', '-', 1, 1, 4),
(42, 0, 'S02', '-', 1, 1, 4),
(43, 1, 'S01', '-', 2, 1, 4),
(44, 0, 'S02', '-', 2, 1, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomsbooked`
--

CREATE TABLE `roomsbooked` (
  `roomsBookedID` int(11) NOT NULL,
  `roomID` int(11) NOT NULL,
  `fk_bookingID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomstatus`
--

CREATE TABLE `roomstatus` (
  `roomStatusID` int(11) NOT NULL,
  `roomStatus` varchar(45) NOT NULL,
  `roomStatusDescription` varchar(45) NOT NULL,
  `roomStatusActive` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `roomstatus`
--

INSERT INTO `roomstatus` (`roomStatusID`, `roomStatus`, `roomStatusDescription`, `roomStatusActive`) VALUES
(1, 'FREE', 'cleaned and ready to go', 1),
(2, 'OCCUPIED', 'guest is in the rooms', 1),
(3, 'IN_SERVICE', 'cleaning in progress', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomtype`
--

CREATE TABLE `roomtype` (
  `roomTypeID` int(11) NOT NULL,
  `roomType` varchar(45) NOT NULL,
  `roomTypeDescription` int(11) NOT NULL,
  `roomTypeActive` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `roomtype`
--

INSERT INTO `roomtype` (`roomTypeID`, `roomType`, `roomTypeDescription`, `roomTypeActive`) VALUES
(1, 'PRIME', 100, 1),
(2, 'COMFORT', 80, 1),
(3, 'STANDARD', 60, 1),
(4, 'BASIC', 40, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `staff`
--

CREATE TABLE `staff` (
  `staffID` int(11) NOT NULL,
  `staffPositionID` varchar(45) NOT NULL,
  `staffFirstName` varchar(45) NOT NULL,
  `staffLastName` varchar(45) NOT NULL,
  `staffAddress` varchar(45) NOT NULL,
  `staffCity` varchar(45) NOT NULL,
  `staffState` varchar(45) NOT NULL,
  `staffZipCode` varchar(45) NOT NULL,
  `staffCountry` varchar(45) NOT NULL,
  `staffPhoneNumber` varchar(45) NOT NULL,
  `staffEmailAddress` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='																	';

--
-- Daten für Tabelle `staff`
--

INSERT INTO `staff` (`staffID`, `staffPositionID`, `staffFirstName`, `staffLastName`, `staffAddress`, `staffCity`, `staffState`, `staffZipCode`, `staffCountry`, `staffPhoneNumber`, `staffEmailAddress`) VALUES
(1, '0001', 'Hugo', 'Boss', 'BossRoad', 'BossCity', 'Berlin', '11000', 'GER', '+49 324 83 84', 'boss@hotel.de'),
(2, '0002', 'Billy', 'Receptionist', 'ReceptionRoad', 'ReceptionCity', 'Berlin', '11000', 'GER', '+49 348 11 11', 'receptionist@hotelde'),
(3, '0001', 'Andrew', 'Boss', 'BossRoad', 'Wien', 'Wien', '1100', 'AUT', '+43 149 34 32', 'boss2@hotel.at'),
(4, '0002', 'Mark', 'Receptionist', 'ReceptionRoad', 'Wien', 'Wien', '1100', 'AUT', '+43 599 09 34', 'receptionist2@hotel.at');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `staffrooms`
--

CREATE TABLE `staffrooms` (
  `staffRoomsID` int(11) NOT NULL,
  `staffRooms` varchar(45) NOT NULL,
  `fk_staffID` int(11) NOT NULL,
  `fk_roomID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `staffrooms`
--

INSERT INTO `staffrooms` (`staffRoomsID`, `staffRooms`, `fk_staffID`, `fk_roomID`) VALUES
(1, 'Office', 1, 41),
(2, 'Office', 3, 43),
(3, 'receptionRoom', 2, 42),
(4, 'receptionRoom', 4, 44);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`bookingID`),
  ADD KEY `fk_bookings_guests_idx` (`fk_guestID`),
  ADD KEY `fk_bookings_reservationAgents_idx` (`fk_reservationAgentID`),
  ADD KEY `fk_bookings_bookingstatus1_idx` (`fk_bookingStatusID`),
  ADD KEY `fk_bookings_hotels1_idx` (`fk_hotelID`);

--
-- Indizes für die Tabelle `bookingstatus`
--
ALTER TABLE `bookingstatus`
  ADD PRIMARY KEY (`bookingStatusID`);

--
-- Indizes für die Tabelle `guests`
--
ALTER TABLE `guests`
  ADD PRIMARY KEY (`guestID`);

--
-- Indizes für die Tabelle `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`hotelID`);

--
-- Indizes für die Tabelle `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`paymentID`),
  ADD KEY `fk_payments_rooms1_idx` (`fk_roomID`),
  ADD KEY `fk_payments_paymentStatus1_idx` (`fk_paymentTypeID`),
  ADD KEY `fk_payments_paymentStatus2_idx` (`fk_paymentStatusID`);

--
-- Indizes für die Tabelle `paymentstatus`
--
ALTER TABLE `paymentstatus`
  ADD PRIMARY KEY (`paymentStatusID`);

--
-- Indizes für die Tabelle `paymenttype`
--
ALTER TABLE `paymenttype`
  ADD PRIMARY KEY (`paymentTypeID`);

--
-- Indizes für die Tabelle `rates`
--
ALTER TABLE `rates`
  ADD PRIMARY KEY (`rateID`),
  ADD KEY `fk_rates_rooms1_idx` (`fk_roomID`),
  ADD KEY `fk_rates_rateTypes1_idx` (`rateTypes_rateTypeID`);

--
-- Indizes für die Tabelle `ratetypes`
--
ALTER TABLE `ratetypes`
  ADD PRIMARY KEY (`rateTypeID`);

--
-- Indizes für die Tabelle `reservationagents`
--
ALTER TABLE `reservationagents`
  ADD PRIMARY KEY (`reservationAgentID`);

--
-- Indizes für die Tabelle `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`roomID`),
  ADD KEY `fk_rooms_hotels1_idx` (`fk_hotelID`),
  ADD KEY `fk_rooms_roomStatus1_idx` (`fk_roomStatusID`),
  ADD KEY `fk_rooms_roomType1_idx` (`fk_roomTypeID`);

--
-- Indizes für die Tabelle `roomsbooked`
--
ALTER TABLE `roomsbooked`
  ADD PRIMARY KEY (`roomsBookedID`,`roomID`),
  ADD KEY `fk_roomsBooked_rooms1_idx` (`roomID`),
  ADD KEY `fk_roomsBooked_bookings1_idx` (`fk_bookingID`);

--
-- Indizes für die Tabelle `roomstatus`
--
ALTER TABLE `roomstatus`
  ADD PRIMARY KEY (`roomStatusID`);

--
-- Indizes für die Tabelle `roomtype`
--
ALTER TABLE `roomtype`
  ADD PRIMARY KEY (`roomTypeID`);

--
-- Indizes für die Tabelle `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`);

--
-- Indizes für die Tabelle `staffrooms`
--
ALTER TABLE `staffrooms`
  ADD PRIMARY KEY (`staffRoomsID`),
  ADD KEY `fk_staffRooms_staff1_idx` (`fk_staffID`),
  ADD KEY `fk_staffRooms_rooms1_idx` (`fk_roomID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `bookings`
--
ALTER TABLE `bookings`
  MODIFY `bookingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT für Tabelle `bookingstatus`
--
ALTER TABLE `bookingstatus`
  MODIFY `bookingStatusID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `guests`
--
ALTER TABLE `guests`
  MODIFY `guestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT für Tabelle `hotels`
--
ALTER TABLE `hotels`
  MODIFY `hotelID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `payments`
--
ALTER TABLE `payments`
  MODIFY `paymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT für Tabelle `paymentstatus`
--
ALTER TABLE `paymentstatus`
  MODIFY `paymentStatusID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `paymenttype`
--
ALTER TABLE `paymenttype`
  MODIFY `paymentTypeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `rates`
--
ALTER TABLE `rates`
  MODIFY `rateID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `ratetypes`
--
ALTER TABLE `ratetypes`
  MODIFY `rateTypeID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `reservationagents`
--
ALTER TABLE `reservationagents`
  MODIFY `reservationAgentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `rooms`
--
ALTER TABLE `rooms`
  MODIFY `roomID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT für Tabelle `roomsbooked`
--
ALTER TABLE `roomsbooked`
  MODIFY `roomsBookedID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT für Tabelle `roomstatus`
--
ALTER TABLE `roomstatus`
  MODIFY `roomStatusID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `roomtype`
--
ALTER TABLE `roomtype`
  MODIFY `roomTypeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `staff`
--
ALTER TABLE `staff`
  MODIFY `staffID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `fk_bookings_bookingstatus` FOREIGN KEY (`fk_bookingStatusID`) REFERENCES `bookingstatus` (`bookingStatusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_bookings_guests` FOREIGN KEY (`fk_guestID`) REFERENCES `guests` (`guestID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_bookings_hotels1` FOREIGN KEY (`fk_hotelID`) REFERENCES `hotels` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_bookings_reservationAgents` FOREIGN KEY (`fk_reservationAgentID`) REFERENCES `reservationagents` (`reservationAgentID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `fk_payments_paymentStatus1` FOREIGN KEY (`fk_paymentTypeID`) REFERENCES `paymenttype` (`paymentTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_payments_paymentStatus2` FOREIGN KEY (`fk_paymentStatusID`) REFERENCES `paymentstatus` (`paymentStatusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_payments_rooms1` FOREIGN KEY (`fk_roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `rates`
--
ALTER TABLE `rates`
  ADD CONSTRAINT `fk_rates_rateTypes1` FOREIGN KEY (`rateTypes_rateTypeID`) REFERENCES `ratetypes` (`rateTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rates_rooms1` FOREIGN KEY (`fk_roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `fk_rooms_hotels1` FOREIGN KEY (`fk_hotelID`) REFERENCES `hotels` (`hotelID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rooms_roomStatus1` FOREIGN KEY (`fk_roomStatusID`) REFERENCES `roomstatus` (`roomStatusID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rooms_roomType1` FOREIGN KEY (`fk_roomTypeID`) REFERENCES `roomtype` (`roomTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `roomsbooked`
--
ALTER TABLE `roomsbooked`
  ADD CONSTRAINT `fk_roomsBooked_bookings` FOREIGN KEY (`fk_bookingID`) REFERENCES `bookings` (`bookingID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_roomsBooked_rooms` FOREIGN KEY (`roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `staffrooms`
--
ALTER TABLE `staffrooms`
  ADD CONSTRAINT `fk_staffRooms_rooms1` FOREIGN KEY (`fk_roomID`) REFERENCES `rooms` (`roomID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_staffRooms_staff1` FOREIGN KEY (`fk_staffID`) REFERENCES `staff` (`staffID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
