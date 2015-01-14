/* Data warehouse */

/* Drop tables */
IF EXISTS(SELECT name 
   FROM   sysobjects 
   WHERE  name = 'Rental'
   AND   type = 'U')
    DROP TABLE Rental

IF EXISTS(SELECT name 
   FROM   sysobjects 
   WHERE  name = 'Province'
   AND   type = 'U')
    DROP TABLE Province

IF EXISTS(SELECT name 
   FROM   sysobjects 
   WHERE  name = 'CategoryDim'
   AND   type = 'U')
    DROP TABLE CategoryDim

IF EXISTS(SELECT name 
   FROM   sysobjects 
   WHERE  name = 'TimeDim'
   AND   type = 'U')
    DROP TABLE TimeDim



/* Create tables */

CREATE TABLE Province
(COD_P INT identity NOT NULL,
 Province CHAR(15) NOT NULL,
 Region  CHAR(15) NOT NULL,
 PRIMARY KEY(COD_P)
);

CREATE TABLE TimeDim
(COD_T INT  identity NOT NULL,
 Month INT NOT NULL,
 Quarter INT NOT NULL,
 FourMonth_Period INT NOT NULL,
 Year INT NOT NULL,
 PRIMARY KEY(COD_T)
);

CREATE TABLE CategoryDim
(COD_C INT  identity NOT NULL,
 Category CHAR(20) NOT NULL,
 PRIMARY KEY(COD_C)
);

CREATE TABLE Rental
(COD_T INT NOT NULL,
 COD_C INT NOT NULL,
 COD_P INT NOT NULL,
 NetIncome FLOAT NOT NULL,
 NumberOfRentedItems INT NOT NULL,
 PRIMARY KEY(COD_T,COD_C,COD_P),
 FOREIGN KEY(COD_T) REFERENCES TimeDim(COD_T) on delete cascade on update cascade,
 FOREIGN KEY(COD_C) REFERENCES CategoryDim(COD_C) on delete cascade on update cascade,
 FOREIGN KEY(COD_P) REFERENCES Province(COD_P) on delete cascade on update cascade,
);


