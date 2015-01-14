/*Script for the creation of the table storing extra contextual information for the analysis of the service requests. 
Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Non_emergency_data_Torino_Unit`
--

-- --------------------------------------------------------

--
-- Schema of table`citizens_per_area` `/*This table reports, for each city area, the number of female and male inhabitants, and the total number of inhabitants*/
--

CREATE TABLE IF NOT EXISTS `citizens_per_area` (
`id` int(11) NOT NULL,
`Area` varchar(50) NOT NULL,
`Females` int(11) NOT NULL,
`Males` int(11) NOT NULL,
`Total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
