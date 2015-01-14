/*Script to feed data warehouse 'Non_emergency_data_Torino_Unit_dw' from table `staging_area`. This script feeds dimensional table `service` from table `staging_area`.
Copyright (c) 2014 Politecnico di Torino
Released under MIT license*/
insert into non_emergency_data_torino_unit.service (`Group`, `Service_Name`)
select Category,Subcategory
from non_emergency_data_torino_unit.staging_area
group by Category,Subcategory