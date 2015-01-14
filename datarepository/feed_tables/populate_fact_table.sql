/*Script to feed data warehouse 'Non_emergency_data_Torino_Unit_dw' from table `staging_area`. This script feeds fact table 'service_request' from table `staging_area`.
Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/
insert into non_emergency_data_torino_unit.service_request (Service_key,Location_key,Date_key,Time_key,Number_Services_Requested)
SELECT S.`Service_key`, L.`Location_key`, D.`Date_key`,T.`Time_Key`, count(*) as Number_Service_Request
FROM (select * from non_emergency_data_torino_unit.staging_area) as Sa  
join non_emergency_data_torino_unit.service as S on (S.`Group`=Sa.Category AND S.`Service_Name`=Sa.Subcategory) 
join non_emergency_data_torino_unit.location as L on (L.`Address`=Sa.Address AND L.`Area`=Sa.Area AND
L.`District`=Sa.District AND L.`Green_Area_Name`=Sa.Green_Area_Name AND
L.`Green_Area_Type`=Sa.Green_Area_Type AND L.`Green_Area_Indicator`=Sa.Green_Area_Indicator)
join non_emergency_data_torino_unit.requested_date as D on (D.`Requested_Date`=Sa.Date AND D.`Holiday_Indicator`=Sa.Holiday_Indicator AND D.`Day_of_Month`=Sa.Day_of_the_Month AND
D.`Day_of_Week`=Sa.Day_of_the_Week AND D.`Month`=Sa.Month AND
D.`Month_of_the_year`=Sa.Month_of_the_year AND D.`Two_Month`=Sa.Two_Month AND
D.`Two_Month_of_the_year`=Sa.Two_Month_of_the_year AND D.`Six_Month`=Sa.Six_Month AND
D.`Six_Month_of_the_year`=Sa.Six_Month_of_the_Year AND D.`Year`=Sa.Year)
join non_emergency_data_torino_unit.requested_time as T on (T.`Requested_Time`=Sa.Time AND
T.`Time_slot`=Sa.Time_slot OR (T.`Time_slot` IS NULL AND Sa.Time_slot IS NULL))

group by  S.`Service_key`, L.`Location_key`, D.`Date_key`,T.`Time_Key`

order by Number_Service_Request desc