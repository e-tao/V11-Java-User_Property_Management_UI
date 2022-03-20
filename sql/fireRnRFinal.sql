-- --------------------------------------------------------
-- Host:                         192.168.1.15
-- Server version:               10.6.4-MariaDB-1:10.6.4+maria~focal - mariadb.org binary distribution
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for firernr
DROP DATABASE IF EXISTS `firernr`;
CREATE DATABASE IF NOT EXISTS `firernr` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `firernr`;

-- Dumping structure for table firernr.booking
DROP TABLE IF EXISTS `booking`;
CREATE TABLE IF NOT EXISTS `booking` (
  `bookingID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `propertyID` int(10) unsigned NOT NULL,
  `UserID` int(10) unsigned NOT NULL COMMENT 'Guest user',
  `bookingTime` date NOT NULL,
  `bookedFrom` date NOT NULL,
  `bookedUntil` date NOT NULL,
  `bookingConfirmation` tinyint(1) unsigned NOT NULL DEFAULT 0,
  `paymentConfirmation` tinyint(3) unsigned NOT NULL DEFAULT 0,
  `paymentMethod` enum('Credit Card','Paypal') DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `bookingExpires` int(1) unsigned NOT NULL DEFAULT 3,
  PRIMARY KEY (`bookingID`),
  KEY `FK_booking_property` (`propertyID`),
  CONSTRAINT `FK_booking_property` FOREIGN KEY (`propertyID`) REFERENCES `property` (`propertyID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table firernr.booking: ~2 rows (approximately)
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` (`bookingID`, `propertyID`, `UserID`, `bookingTime`, `bookedFrom`, `bookedUntil`, `bookingConfirmation`, `paymentConfirmation`, `paymentMethod`, `paymentDate`, `bookingExpires`) VALUES
	(1, 1, 9, '2021-04-23', '2021-04-28', '2021-05-10', 1, 1, 'Credit Card', '2021-04-25', 3),
	(2, 1, 2, '2021-05-29', '2021-06-03', '2021-06-07', 1, 1, 'Paypal', '2021-05-30', 3),
	(3, 1, 10, '2021-09-05', '2021-09-10', '2021-09-14', 1, 1, 'Credit Card', '2021-09-07', 3),
	(4, 1, 5, '2021-09-27', '2021-10-08', '2021-10-12', 0, 0, NULL, NULL, 3),
	(5, 2, 5, '2021-06-29', '2021-07-04', '2021-07-09', 1, 1, 'Credit Card', '2021-06-29', 3),
	(6, 2, 8, '2021-08-15', '2021-08-19', '2021-08-24', 1, 1, 'Credit Card', '2021-08-16', 3),
	(7, 2, 3, '2021-09-01', '2021-09-03', '2021-09-09', 1, 1, 'Paypal', '2021-09-03', 3),
	(8, 3, 14, '2021-10-04', '2021-12-23', '2022-01-04', 0, 0, NULL, NULL, 3),
	(9, 3, 16, '2021-06-20', '2021-06-29', '2021-07-04', 1, 1, 'Credit Card', '2021-06-22', 3),
	(10, 3, 6, '2021-07-27', '2021-08-07', '2021-08-11', 1, 1, 'Paypal', '2021-07-29', 3),
	(11, 4, 8, '2021-09-29', '2021-10-08', '2021-10-12', 1, 1, 'Paypal', '2021-09-30', 3),
	(12, 5, 9, '2021-04-29', '2021-07-29', '2021-08-05', 0, 0, NULL, NULL, 3),
	(13, 5, 7, '2021-07-21', '2021-08-01', '2021-08-06', 1, 1, 'Credit Card', '2021-07-22', 3),
	(14, 6, 2, '2020-12-22', '2021-01-01', '2021-01-10', 1, 1, 'Credit Card', '2020-12-23', 3),
	(15, 6, 15, '2021-04-28', '2021-05-03', '2021-05-11', 1, 1, 'Credit Card', '2021-04-29', 3),
	(16, 6, 17, '2021-07-14', '2021-08-25', '2021-09-01', 0, 0, NULL, NULL, 3),
	(17, 6, 4, '2021-07-11', '2021-08-19', '2021-08-23', 1, 1, 'Credit Card', '2021-07-11', 3),
	(18, 7, 15, '2021-09-29', '2021-12-22', '2021-12-30', 1, 1, 'Credit Card', '2021-09-30', 3);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;

-- Dumping structure for table firernr.log
DROP TABLE IF EXISTS `log`;
CREATE TABLE IF NOT EXISTS `log` (
  `logID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee` varchar(8) NOT NULL DEFAULT '0',
  `event` text NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`logID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table firernr.log: ~0 rows (approximately)
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` (`logID`, `employee`, `event`, `date`, `time`) VALUES
	(5, '12345678', '[Changed from 0 to 483-203-209]', '2021-10-21', '00:00:00'),
	(6, '38293052', '[Changed from 0 to 129-098-392]', '2021-10-21', '00:00:00'),
	(7, '12345678', '[Changed from 0 to 304-039-908, Changed from AshleyJKillough@jourrapide.com to AshleyJKillough@gmail.com]', '2021-10-21', '00:00:00'),
	(8, '12039289', '[Changed from 0 to 123-998-001]', '2021-10-21', '18:40:46'),
	(9, '99999999', '[Changed from 0 to 999-888-777]', '2021-10-21', '19:11:28'),
	(10, '34567890', 'Purge inactive user', '2021-10-21', '19:52:23'),
	(11, '34567890', 'Purge inactive user', '2021-10-21', '20:00:00');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;

-- Dumping structure for table firernr.property
DROP TABLE IF EXISTS `property`;
CREATE TABLE IF NOT EXISTS `property` (
  `propertyID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `propertyName` varchar(50) NOT NULL,
  `propertyAddrSuitNo` varchar(10) DEFAULT NULL,
  `propertyAddrNo` varchar(50) NOT NULL,
  `propertyAddrStreet` varchar(50) NOT NULL,
  `propertyAddr2ndLine` varchar(50) DEFAULT NULL,
  `propertyAddrCity` varchar(50) NOT NULL,
  `propertyAddrProv` varchar(50) NOT NULL,
  `propertyAddrCountry` varchar(50) NOT NULL,
  `propertyPostalCode` varchar(7) NOT NULL,
  `propertyType` enum('Apartment','House') NOT NULL,
  `propertyDescription` text DEFAULT NULL,
  `propertyPhoto` varchar(50) DEFAULT NULL,
  `noOfRooms` int(2) unsigned NOT NULL,
  PRIMARY KEY (`propertyID`),
  UNIQUE KEY `propertyName_propertyAddress` (`propertyName`,`propertyAddrSuitNo`,`propertyAddrNo`,`propertyAddrStreet`,`propertyAddrCity`,`propertyAddrProv`,`propertyAddrCountry`,`propertyPostalCode`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table firernr.property: ~0 rows (approximately)
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` (`propertyID`, `propertyName`, `propertyAddrSuitNo`, `propertyAddrNo`, `propertyAddrStreet`, `propertyAddr2ndLine`, `propertyAddrCity`, `propertyAddrProv`, `propertyAddrCountry`, `propertyPostalCode`, `propertyType`, `propertyDescription`, `propertyPhoto`, `noOfRooms`) VALUES
	(1, 'Crib On Lake', NULL, '1850', 'E River Road', NULL, 'Ottawa', 'ON', 'Canada', 'K4M 1B4', 'House', 'Beautiful 15 year old year-round Viceroy cottage available. 4+ bedrooms, 3 bathrooms, large deck and property. Close to Bayport Marina and local beaches. Road allowance leading to waterfront 3 houses away. Well equipped - BBQ, Linens, Kitchenware, Washer, dryer and lots of parking. New hardwood floors on main level and kitchen cupboards and appliances updated . Quiet neighbourhood.', 'https://imgur.com/6vqxgQb', 4),
	(2, 'Beach Vibes', NULL, '2586 ', 'Dwyer Hill Road', NULL, 'Ottawa', 'ON', 'Canada', 'K0A 1B0', 'House', 'Live like a local in the Heart of Port Stanley! Perfect for work or play, you will feel right at home. 10 mins walk to the beach of lake Erie, walking distance to grocery store and the post office. Enjoy the view of the creek and all the fun that Port Stanley has to offer', 'https://imgur.com/gallery/iE6jlV6', 3),
	(3, 'City Skyline', '1106', '535 ', 'Fisgard Street', NULL, 'Victoria', 'BC', 'Canada', 'V8W 1R3', 'Apartment', 'A unique investment immersed in Victoria\'s most historic neighbourhood! This 2-level condo, situated in the Lum Sam & Lee Chong building, was restored & renovated by renowned local developer Chris Lefevre in 2017.', 'https://imgur.com/gallery/w54wY', 2),
	(4, 'Georgia Garden', '503', '106', 'Keefer St', NULL, 'Vancouver', 'BC', 'Canada', 'V6A 1X4', 'Apartment', 'This spacious, bright, one-bedroom condo is surprisingly quiet despite it\'s close proximity to the heart of downtown Vancouver.', 'https://imgur.com/gallery/XTy1N0C', 1),
	(5, 'Birdhouse', NULL, '58', 'Army Beach', NULL, 'Whitehorse South', 'YT', 'Canada', 'Y0B 1Y1', 'House', 'Welcome to our little Birdhouse!', 'https://imgur.com/gallery/FtDrF', 3),
	(6, 'Cottage On the Cove', NULL, '12870', 'Peggy\'s Cove Road', NULL, 'Halifax', 'NS', 'Canada', 'B3Z 2M2', 'House', 'Peaceful & secluded on the shore of St Margaret\'s Bay', 'https://imgur.com/gallery/uVZAh', 2),
	(7, 'Au Bord de l eau', '702', '329', 'Rue de l\'Hêtrière', NULL, 'Saint-Augustin-de-Desmaures', 'QC', 'Canada', 'G3A 2Z4', 'Apartment', 'Located in Pincourt, this 2-bedroom, 1-bathroom vacation home lets you experience it all. Fans of nature and the outdoors will love being 13 minutes by car from Morgan Arboretum and 17 minutes from Saint Anne Rapids. If you\'d like to explore the area, you can make the 6-minute drive to Iles-Avelle-Wight-et-Hiam Ecological Reserve or the 13-minute drive to Arena Cite Des Jeunes. While you\'re here, you can enjoy all the comforts of home and more, including WiFi, air conditioning, and a dryer.', 'https://imgur.com/gallery/bmFaD', 2),
	(8, 'La Maison Laurentienne', NULL, '211', 'Av. Crémazie', NULL, 'Baie-Comeau', 'QC', 'Canada', 'G4Z 2P4', 'House', 'Un chez-soi près des services! Cette maison mobile possède une annexe qui fait office de hall d\'entrée, deux chambres à coucher et un air de vie lumineux. Profitez de son grand terrain sans voisin arrière pour relaxer autour d\'un feu. Venez visiter pour découvrir les avantages qu\'elle peut vous offrir. (36846968)', 'https://imgur.com/gallery/9sEzy', 2);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;

-- Dumping structure for table firernr.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(15) NOT NULL,
  `userFirstName` varchar(15) NOT NULL,
  `userMiddlename` varchar(15) DEFAULT NULL,
  `userLastName` varchar(15) NOT NULL,
  `gender` enum('M','F') DEFAULT NULL,
  `mailAddrSuitNo` varchar(10) DEFAULT NULL,
  `mailAddrStreetNo` varchar(10) NOT NULL,
  `mailAddrStreet` varchar(50) NOT NULL,
  `mailAddr2ndLine` varchar(50) DEFAULT NULL,
  `mailAddrCity` varchar(30) NOT NULL,
  `mailAddrProv` varchar(30) NOT NULL,
  `mailAddrCountry` varchar(30) NOT NULL,
  `mailPostalCode` varchar(7) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneNo` varchar(20) DEFAULT NULL,
  `dateJoined` date NOT NULL,
  `lastLogin` date DEFAULT NULL,
  `lastReservation` date DEFAULT NULL,
  `accumulatedRate` decimal(4,2) unsigned NOT NULL DEFAULT 0.00,
  `accountActivation` tinyint(1) unsigned NOT NULL DEFAULT 1,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName` (`userName`),
  UNIQUE KEY `userName_userAddress` (`userFirstName`,`userMiddlename`,`userLastName`,`mailAddrStreetNo`,`mailAddrStreet`,`mailAddrCity`,`mailAddrProv`,`mailAddrCountry`,`mailPostalCode`,`mailAddrSuitNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table firernr.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`, `userName`, `userFirstName`, `userMiddlename`, `userLastName`, `gender`, `mailAddrSuitNo`, `mailAddrStreetNo`, `mailAddrStreet`, `mailAddr2ndLine`, `mailAddrCity`, `mailAddrProv`, `mailAddrCountry`, `mailPostalCode`, `email`, `phoneNo`, `dateJoined`, `lastLogin`, `lastReservation`, `accumulatedRate`, `accountActivation`) VALUES
	(1, 'dksaldf', 'Shane', 'Gena', 'Bobber', NULL, NULL, '3879', 'rue Garneau', NULL, 'Quebec', 'QC', 'Canada', 'G1V 3V5', 'ShaneLLemmons@dayrep.com', '123-998-001', '2016-09-28', '2016-09-28', NULL, 0.00, 1),
	(2, 'ekdkelw', 'Christopher', NULL, 'Mahr', 'M', NULL, '3429', 'Bay Street', NULL, 'Toronto', 'ON', 'Canada', 'M5J 2R8', 'ChristopherLMahr@armyspy.com', '647-839-9609', '2018-04-28', '2020-09-28', '2021-05-29', 3.00, 1),
	(3, 'xirpoyt', 'Carol ', NULL, 'Robinson', 'F', NULL, '1932', '11th Ave', NULL, 'Calgary', 'AB', 'Canada', 'T2P 1M6', 'CarolCRobinson@rhyta.com', '123-000-000', '2019-09-28', '2021-09-28', '2021-09-01', 4.00, 1),
	(4, 'eoeksfd', 'Joseph', 'Bercnan', 'Wagner', 'M', '3', '2690', 'MacLaren Street', NULL, 'Ottawa', 'ON', 'Canada', 'K1P 5M7', 'JosephAWagner@teleworm.us', '691 450 985', '2014-02-15', '2020-05-01', '2021-09-29', 2.50, 1),
	(5, 'eueojfk', 'Sabrina', NULL, 'Moreau', NULL, NULL, '2086', 'Park Ct', NULL, 'Robb', 'AB', 'Canada', 'T0E 1X0', 'SabrinaRMoreau@dayrep.com', '123-909-000', '2017-09-28', '2021-02-28', '2021-09-27', 5.00, 1),
	(6, 'sdiwerej', 'Helen', NULL, 'Costantino', 'F', '2', '3400', 'Burdett Avenue', NULL, 'Victoria', 'BC', 'Canada', 'V8W 1B2', 'LeannRParsley@jourrapide.com', '483-203-209', '2015-09-28', '2017-04-02', '2021-09-29', 4.30, 1),
	(7, 'weoixjx', 'Gary', NULL, 'Majors', 'M', NULL, '4988 ', 'Dufferin Street', NULL, 'Toronto', 'ON', 'Canada', 'M6H 4B6', 'GaryMMajors@rhyta.com', '416-534-1208', '2019-09-23', '2019-09-23', '2021-07-21', 2.10, 1),
	(8, 'fjjyhhgf', 'Rebecca', 'Robena', 'Lopez', 'F', NULL, '2371', 'Northumberland Street', NULL, 'New Dundee', 'ON', 'Canada', 'N0B 2E0', 'RebeccaRLopez@armyspy.com', '519-696-3562', '2017-09-23', '2017-09-28', '2021-09-29', 1.80, 1),
	(9, 'kjggjg', 'Josh', NULL, 'Riggs', NULL, '12', '588', 'Lauzon Parkway', NULL, 'La Salle', 'ON', 'Canada', 'N9J 1M9', 'JoshCRiggs@dayrep.com', '129-098-392', '2013-05-24', '2017-10-28', '2021-09-29', 4.90, 1),
	(10, 'iweoisdn', 'Jeffrey', NULL, 'Thomas', 'M', NULL, '3891', 'rue Parc', NULL, 'Sherbrooke', 'QC', 'Canada', 'J1H 5M7', 'JeffreyEThomas@armyspy.com', '819-580-3496', '2019-09-28', '2019-09-28', '2021-09-29', 5.00, 1),
	(13, 'udhsy3d', 'Leonard', 'Romilda	', 'Hersom', 'F', NULL, '122', 'Bayfield St', NULL, 'Woodbridge', 'ON', 'Canada', 'L4L 8Z7', 'LeonardLHersom@teleworm.us', '905-652-2477', '2021-03-28', '2021-05-28', NULL, 0.00, 1),
	(14, 'iewhkjsd8', 'David', NULL, 'Menard', 'M', NULL, '3329 ', 'Toy Avenue', NULL, 'Pickering', 'ON', 'Canada', 'L1S 6L6', 'DavidMMenard@armyspy.com', '192-098-302', '2020-07-28', '2021-06-28', '2021-09-29', 4.30, 1),
	(15, '349898dj', 'Ashley', NULL, 'Killough', NULL, NULL, '2157', 'Granville St', NULL, 'Halifax', 'NS', 'Canada', 'B3K 2Y6', 'AshleyJKillough@gmail.com', '304-039-908', '2018-09-28', '2020-07-28', '2021-09-29', 4.00, 1),
	(16, '98jkw3j', 'Timothy', NULL, 'Bobby', 'M', NULL, '2213', 'MacLaren Street', NULL, 'Ottawa', 'ON', 'Canada', 'K1P 5M7', 'TimothyLBobby@jourrapide.com', '613-564-6295', '2019-09-28', '2021-07-28', '2021-06-20', 1.50, 1),
	(17, '3498eekjf', 'Valerie', NULL, 'Staff', 'F', '402', '3361 ', 'Keith Road', NULL, 'Vancouver', 'BC', 'Canada', 'V5T 2C1', 'ValerieMStaff@dayrep.com', '999-888-777', '2016-09-28', '2020-12-23', '2021-07-14', 1.40, 1),
	(18, 'wrwerfd', 'Carol', NULL, 'Bobby', NULL, NULL, '324', 'rue Landig', NULL, 'Gatineau', 'QC', 'Canada', 'K2S 3K2', 'dkalfj@gmail.com', NULL, '0000-00-00', NULL, NULL, 0.00, 0),
	(19, 'dsuflkajw', 'David', NULL, 'Staff', NULL, NULL, '2', 'rue Chalxo', NULL, 'Ottawa', 'ON', 'Canada', 'K1S 2Kv', '2ljsdlkxljfdf@gmail.com', NULL, '0000-00-00', NULL, NULL, 0.00, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
