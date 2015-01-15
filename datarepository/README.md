Dataset used for the analysis 
-----------------------------

This directory contains the SQL scripts to create the schema of the tables for storing the dataset considered for the analysis. The dataset represents the non-emergency calls dataset with additional information describing the service requests at different temporal and spatial granularity levels. Tables represent the information contained in the dataset through the following data representation models: a relational based representation as in the staging area, a data warehouse with a star schema, and a materialized view, generated from the data warehouse, used for KPIs computation. An additional table represents extra contextual information as demographic statistics about city areas. 

The directory also includes the SQL scripts to feed the data warehouse tables starting from the staging area table, and to feed the materialized view starting from the data warehouse.

### Installation 

Install mysql server 5.x 

### Usage

* To create tables’ schema, run scripts in directory “create_tables” 
* To feed tables, run scripts in directory “feed_tables”
