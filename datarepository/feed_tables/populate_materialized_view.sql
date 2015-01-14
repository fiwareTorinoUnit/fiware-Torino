/*Script to feed materialized view 'view_on_service_request' from data warehouse`Non_emergency_data_Torino_Unit_dw`. 
Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/


insert into non_emergency_data_torino_unit.view_on_service_request (Service_Name,Group, Address, Area, District,Green_Area_Name, Green_Area_Type,Green_Area_Indicator,Requested_Date,Holiday_indicator, Day_of_Month, Day_of_Week, Month, Month_of_the_year, Two_Month, Two_Month_of_the_year, Six_Month, Six_Month_of_the_year,Year, Tot_Num_Service_Requests) 
SELECT S.`Service_Name`,S.`Group`, L.`Address`, L.`Area`, L.`District`,L.`Green_Area_Name`, L.`Green_Area_Type`,L.`Green_Area_Indicator`,D.`Requested_Date`,D.`Holiday_indicator`, D.`Day_of_Month`, D.`Day_of_Week`, D.`Month`, D.`Month_of_the_year`, D.`Two_Month`, D.`Two_Month_of_the_year`, D.`Six_Month`, D.`Six_Month_of_the_year`,D.`Year`, count(*) AS `Tot_Num_Service_Requests` 
FROM non_emergency_data_torino_unit.service_request as SR, 
non_emergency_data_torino_unit.location as L, non_emergency_data_torino_unit.service as S, non_emergency_data_torino_unit.requested_date as D,

WHERE SR.Service_key=S.Service_key AND SR.Location_key = L.Location_key AND SR.Date_key = D.Date_key

GROUP BY  SR.`Service_key`, SR.`Location_key`, SR.`Date_key`,SR.`Time_Key`




 

  



