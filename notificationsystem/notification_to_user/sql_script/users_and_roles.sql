/*Script for the creation of the database related to the user registration and role subscription. 
Copyright (c) 2014 Politecnico di Torino*/

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `users_and_roles`
--
CREATE DATABASE IF NOT EXISTS `users_and_roles` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `users_and_roles`;

-- --------------------------------------------------------

--
-- Schema of table `area_district`/*This table associates each city area with the corresponding city district*/
--

CREATE TABLE IF NOT EXISTS `area_district` (
  `id` int(11) NOT NULL,
  `Name` varchar(200) NOT NULL,
  `id_District` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Schema of table `kpis`
/*This table lists the KPIs supported by the notification system*/--

CREATE TABLE IF NOT EXISTS `kpis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Kpi_name` varchar(200) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `District` varchar(200) NOT NULL,
  `Path` varchar(1000) NOT NULL,
  `Granularity` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=25 ;

-- --------------------------------------------------------

--
-- Schema of table `kpi_parameters`
/*This table lists the parameters related to each KPI. For KPIs with the spatial granularity of 'city', it lists the reference city districts of the KPI. For KPIs with the spatial granularity of 'district', it lists the city areas for the reference city districts of the KPI. /--
CREATE TABLE IF NOT EXISTS `kpi_parameters` (
  `ID` int(11) NOT NULL,
  `Parameter` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Schema of table `roles`/*This table lists the city roles supported by the notification system*/
--

CREATE TABLE IF NOT EXISTS `roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(200) NOT NULL,
  `Granularity` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=240 ;

-- --------------------------------------------------------

--
-- Schema of the table `roles_kpis`


/*This table associates city roles with one or more KPIs. It is used to store the role subscriptions to KPIs*/--
CREATE TABLE IF NOT EXISTS `roles_kpis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_role` int(11) NOT NULL,
  `ID_kpi` int(11) NOT NULL,
  `Condition` varchar(200) NOT NULL,
  `id_sub` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=108 ;

-- --------------------------------------------------------

--
-- Schema of table `users`
/*This table lists the registered users and their associated city role*/--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) NOT NULL,
  `Surname` varchar(200) NOT NULL,
  `eMail` varchar(200) NOT NULL,
  `ID_role` int(11) NOT NULL,
  `area` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Schema of table `users_quart`
/*For each user registered with granularity city area, this table specifies the city area for the user*/--

CREATE TABLE IF NOT EXISTS `users_quart` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_User` int(11) NOT NULL,
  `Area` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
