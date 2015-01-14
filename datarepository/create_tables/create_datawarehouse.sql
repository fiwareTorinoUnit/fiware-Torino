/*Script for the creation of the Data Warehouse dimensional and fact tables Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Non_emergency_data_Torino_Unit`
--
CREATE DATABASE IF NOT EXISTS `Non_emergency_data_Torino_Unit` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `Non_emergency_data_Torino_Unit`;

-- --------------------------------------------------------

--
-- Schema of dimensional table `location`/*This table stores the address of the service requests at different spatial granularity levels (e.g., street address, city area, city district)*/
--

CREATE TABLE IF NOT EXISTS `location` (
  `Location_Key` int(11) NOT NULL AUTO_INCREMENT,
  `Address` varchar(50) NOT NULL,
  `Area` varchar(50) NOT NULL,
  `District` varchar(2) NOT NULL,
  `Green_Area_Name` varchar(50) NOT NULL,
  `Green_Area_Type` varchar(50) NOT NULL,
  `Green_Area_Indicator` varchar(20) NOT NULL,
  PRIMARY KEY (`Location_Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Schema of dimensional table `requested_date`/*This table stores the date of the service requests at different temporal granularity levels (e.g, date, month, year)*/
--

CREATE TABLE IF NOT EXISTS `requested_date` (
  `Date_Key` int(11) NOT NULL AUTO_INCREMENT,
  `Requested_Date` date NOT NULL,
  `Holiday_indicator` varchar(50) NOT NULL,
  `Day_of_Month` int(11) NOT NULL,
  `Day_of_Week` varchar(50) NOT NULL,
  `Month` varchar(20) NOT NULL,
  `Month_of_the_year` int(11) NOT NULL,
  `Two_Month` varchar(20) NOT NULL,
  `Two_Month_of_the_year` int(11) NOT NULL,
  `Six_Month` varchar(20) NOT NULL,
  `SIx_Month_of_the_year` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  PRIMARY KEY (`Date_Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Schema of dimensional table `requested_time`/*This table stores the time of the service requests at different temporal granularity levels (e.g, time, time slot)*/
--

CREATE TABLE IF NOT EXISTS `requested_time` (
  `Time_Key` int(11) NOT NULL AUTO_INCREMENT,
  `Requested_Time` time DEFAULT NULL,
  `Time_slot` varchar(50) NOT NULL,
  PRIMARY KEY (`Time_Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Schema of dimensional table `service`/*This table stores the group (or category) and service name (or subcategory) of service requests*/
--

CREATE TABLE IF NOT EXISTS `service` (
  `Service_Key` int(11) NOT NULL AUTO_INCREMENT,
  `Service_Name` varchar(100) NOT NULL,
  `Group` varchar(50) NOT NULL,
  PRIMARY KEY (`Service_Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Schema of fact table `service_request`/*This table stores the service requests based on the dimensional tables above.*/
--

CREATE TABLE IF NOT EXISTS `service_request` (
  `Service_key` int(11) NOT NULL,
  `Location_key` int(11) NOT NULL,
  `Date_key` int(11) NOT NULL,
  `Time_Key` int(11) NOT NULL,
  `Number_Services_Requested` int(11) NOT NULL,
  PRIMARY KEY (`Service_key`,`Location_key`,`Time_Key`,`Date_key`)
  FOREIGN KEY (`Service_key`) REFERENCES service(`Service_Key`)
  FOREIGN KEY (`Location_key`) REFERENCES location(`Location_Key`)
  FOREIGN KEY (`Date_key`) REFERENCES requested_date(`Date_Key`)
  FOREIGN KEY (`Time_Key`) REFERENCES requested_time(`Time_Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
