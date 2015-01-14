/* Drop staging table */

IF EXISTS (SELECT name 
    FROM   sysobjects 
    WHERE  name = 'StagingTable' 
    AND    type = 'U')
    DROP TABLE StagingTable

/* Create staging table */

create table StagingTable
(month int not null,
 year int not null,
 province char(15) not null,
 category char(20) not null,
 numberRentedItems int not null,
 netIncome float not null,
 primary key(month,year,province,category)
)
