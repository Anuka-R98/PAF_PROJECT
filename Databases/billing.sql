-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 26, 2022 at 03:20 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `billing`
--

-- --------------------------------------------------------

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
CREATE TABLE IF NOT EXISTS `complaint` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(200) NOT NULL,
  `customerPNO` varchar(10) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `custom`
--

DROP TABLE IF EXISTS `custom`;
CREATE TABLE IF NOT EXISTS `custom` (
  `cID` int(6) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(200) NOT NULL,
  `customerAddress` varchar(200) NOT NULL,
  `customerNIC` varchar(200) NOT NULL,
  `customerEmail` varchar(200) NOT NULL,
  `customerPNO` varchar(200) NOT NULL,
  PRIMARY KEY (`cID`),
  UNIQUE KEY `customerEmail` (`customerEmail`),
  UNIQUE KEY `customerNIC` (`customerNIC`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `custom`
--

INSERT INTO `custom` (`cID`, `customerName`, `customerAddress`, `customerNIC`, `customerEmail`, `customerPNO`) VALUES
(2, 'Ransika', 'Nuwara', '2000011', 'r@gmail.com', '07823232'),
(3, 'Anne', 'Wattala', '33333', 'anne@gmail.com', '111111'),
(7, 'Abc', 'Jaela', '12345678', 'abc@gmail.com', '0711518296'),
(11, 'Ransika', 'Nuwara', '2000', 'ra@gmail.com', '07823232'),
(12, 'Fathima', 'Malabe', '99232323v', 'fathima@gmail.com', '071232345'),
(16, 'Shehani', 'Colombo', '8877665v', 's@gmail.com', '0711212121');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(200) NOT NULL,
  `billId` varchar(200) NOT NULL,
  `cardNo` varchar(200) NOT NULL,
  `ccv` varchar(200) NOT NULL,
  `expiredDate` date NOT NULL,
  PRIMARY KEY (`pID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
CREATE TABLE IF NOT EXISTS `unit` (
  `uID` int(7) NOT NULL AUTO_INCREMENT,
  `customerId` varchar(200) NOT NULL,
  `date` varchar(10) NOT NULL,
  `units` varchar(200) NOT NULL,
  `totalAmount` varchar(200) NOT NULL,
  PRIMARY KEY (`uID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`uID`, `customerId`, `date`, `units`, `totalAmount`) VALUES
(1, 'E8759845', '2022/03/02', '30', '3000'),
(2, 'E4567092', '2022/02/02', '20', '2000');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
