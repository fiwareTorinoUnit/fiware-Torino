/*Script to feed data warehouse 'Non_emergency_data_Torino_Unit_dw' from table `staging_area`. This script feeds dimensional table `location` from table `staging_area`.
Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/
insert into non_emergency_data_torino_unit.location (Address,Area,District,Green_Area_Name,Green_Area_Type,Green_Area_Indicator) 
SELECT Address,Area,District,Green_Area_Name,Green_Area_Type,Green_Area_Indicator FROM non_emergency_data_torino_unit.staging_area group by Address,Area,District,Green_Area_Name,Green_Area_Type,Green_Area_Indicator 