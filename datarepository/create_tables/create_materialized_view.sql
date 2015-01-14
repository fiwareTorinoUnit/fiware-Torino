
/*Script for the creation of the Data Warehouse materialized view. Copyright (c) 2014 Politecnico di Torino
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

-- --------------------------------------------------------

--
-- Schema of table `view_on_service_request`/*The materialized view describes the services requests and it is used for KPIs computation.*/
--

CREATE TABLE IF NOT EXISTS `view_on_service_request` (
  `view_key` int(11) NOT NULL AUTO_INCREMENT,
  `Service_Name` varchar(50) DEFAULT NULL,
  `Group` varchar(50) DEFAULT NULL,
  `Address` varchar(41) DEFAULT NULL,
  `Area` varchar(50) DEFAULT NULL,
  `District` int(2) DEFAULT NULL,
  `Green_Area_Name` varchar(39) DEFAULT NULL,
  `Green_Area_Type` varchar(50) DEFAULT NULL,
  `Green_Area_Indicator` varchar(50) DEFAULT NULL,
  `Requested_Date` date NOT NULL,
  `Holiday_indicator` varchar(7) DEFAULT NULL,
  `Day_of_Month` int(2) DEFAULT NULL,
  `Day_of_Week` int(50) NOT NULL,
  `Month` varchar(10) NOT NULL,
  `Month_of_the_year` int(2) DEFAULT NULL,
  `Two_Month` varchar(50) NOT NULL,
  `Two_Month_of_the_year` int(11) NOT NULL,
  `Six_Month` varchar(50) NOT NULL,
  `Six_Month_of_the_year` int(11) NOT NULL,
  `Year` int(4) DEFAULT NULL,
  `Tot_Num_Service_Requests` int(11) NOT NULL,
  PRIMARY KEY (`view_key`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3340 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
