/* Script to create the schema of two tables used to feed table 'staging area'.
Copyright (c) 2014 Politecnico di Torino
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
-- Schema of table `green_area_address`/*This table lists adresses of green areas*/
--

CREATE TABLE IF NOT EXISTS `green_area_address` (
  `Address` varchar(48) DEFAULT NULL,
  `Name_Green_Area` varchar(39) DEFAULT NULL
  PRIMARY KEY (`Address`,`Name_Green_Area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


--
-- Schema of table `area_addresses`. /*This table associates addresses with the corresponding city area*/
--

CREATE TABLE IF NOT EXISTS `area_addresses` (
  `type` varchar(6) NOT NULL,
  `name` varchar(41) NOT NULL DEFAULT '',
  `area` varchar(19) NOT NULL DEFAULT '',
  PRIMARY KEY (`type`,`name`,`area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
