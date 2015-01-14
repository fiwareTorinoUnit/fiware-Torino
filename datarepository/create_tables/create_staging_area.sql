
/*Script for the creation of the staging area of the Data Warehouse Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database:`Non_emergency_data_Torino_Unit`
--

-- --------------------------------------------------------

--
-- Schema of table `staging_area`/*This table stores the dataset used for the analysis with a relational based representation as in the staging area.*/
--

CREATE TABLE IF NOT EXISTS `staging_area` (
  `Category` varchar(50) DEFAULT NULL,
  `Subcategory` varchar(50) DEFAULT NULL,
  `Address` varchar(41) DEFAULT NULL,
  `Area` varchar(19) DEFAULT NULL,
  `District` int(2) DEFAULT NULL,
  `Green_Area_Name` varchar(39) DEFAULT NULL,
  `Green_Area_Type` varchar(50) NOT NULL,
  `Green_Area_Indicator` varchar(50) DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `Time_slot` varchar(20) NOT NULL,
  `Date` date NOT NULL,
  `Holiday_indicator` varchar(7) DEFAULT NULL,
  `Day_of_the_month` int(2) DEFAULT NULL,
  `Day_of_the_week` int(50) NOT NULL,
  `Month` varchar(10) NOT NULL,
  `Month_of_the_year` int(2) DEFAULT NULL,
  `Two_Month` varchar(50) NOT NULL,
  `Two_Month_of_the_year` int(11) NOT NULL,
  `Six_Month` varchar(50) NOT NULL,
  `Six_Month_of_the_year` int(11) NOT NULL,
  `Year` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
