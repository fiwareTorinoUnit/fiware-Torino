/*Script to feed data warehouse 'Non_emergency_data_Torino_Unit_dw' from table `staging_area`. This script feeds dimensional table `requested_date` from table `staging_area`.
Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/
INSERT INTO non_emergency_data_torino_unit.requested_date(  `Requested_Date` ,  `Holiday_indicator` ,  `Day_of_Month` ,  `Day_of_Week` ,  `Month` , `Month_of_the_year` ,  `Two_Month` ,  `Two_Month_of_the_year` ,  `Six_Month` ,  `SIx_Month_of_the_year` ,  `Year` ) 
SELECT DATE, Holiday_indicator, Day_of_the_Month, Day_of_the_week, 
MONTH , Month_of_the_year, Two_Month, Two_Month_of_the_year, Six_Month, Six_Month_of_the_year, YEAR
FROM non_emergency_data_torino_unit.staging_area
GROUP BY DATE, Holiday_indicator, Day_of_the_Month, Day_of_the_week, 
MONTH , Month_of_the_year, Two_Month, Two_Month_of_the_year, Six_Month, Six_Month_of_the_year, YEAR

