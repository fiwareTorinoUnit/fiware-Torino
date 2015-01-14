/*Script for the creation of the database related to the users registration and roles subscription. Copyright (c) 2014 Politecnico di Torino*/


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

-- 
--------------------------------------------------------

--
-- Schema of table `area_district`/*associates area with its district*/
--

CREATE TABLE IF NOT EXISTS `area_district` (
  `id` int(11) NOT NULL,
  `Name` varchar(200) NOT NULL,
  `id_District` int(11) NOT NULL, PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 

--------------------------------------------------------

--
-- Schema of table `kpis`
/*list of KPIs supported by the system*/--

CREATE TABLE IF NOT EXISTS `kpis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Kpi_name` varchar(200) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `District` varchar(200) NOT NULL,
  `Path` varchar(1000) NOT NULL,
  `Granularity` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=25 ;

-- 

--------------------------------------------------------

--
-- Schema of table `kpi_parameters`
/*list of parameters related to each KPI*/--
CREATE TABLE IF NOT EXISTS `kpi_parameters` (
  `ID` int(11) NOT NULL,
  `Parameter` varchar(200) NOT NULL, PRIMARY KEY (`ID`,`Parameter`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 

--------------------------------------------------------

--
-- Schema of table `roles`/*list of supported roles in the system*/
--

CREATE TABLE IF NOT EXISTS `roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(200) NOT NULL,
  `Granularity` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=240 ;

-- 

--------------------------------------------------------

--
-- Schema of the table `roles_kpis`


/*associates roles with one or more KPIs. used for the subscriptions*/--
CREATE TABLE IF NOT EXISTS `roles_kpis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_role` int(11) NOT NULL,
  `ID_kpi` int(11) NOT NULL,
  `Condition` varchar(200) NOT NULL,
  `id_sub` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=108 ;

-- 

--------------------------------------------------------

--
-- Schema of table `users`
/*list of registered users with the role associated*/--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) NOT NULL,
  `Surname` varchar(200) NOT NULL,
  `eMail` varchar(200) NOT NULL,
  `ID_role` int(11) NOT NULL,
  `area` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

-- 

--------------------------------------------------------

--
-- Schema of table `users_quart`
/*for each registered user with granularity 'area' this table specify the proper area*/--
CREATE TABLE IF NOT EXISTS `users_quart` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_User` int(11) NOT NULL,
  `Area` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;

/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
