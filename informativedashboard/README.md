# Informative dashboard on non-emergency calls

According to the dataset described in directory [../datarepository](/datarepository), an informative dashboard is generated based on a selection of example Key Performance Indicators (KPIs), aimed at providing information on the citizens' perception on urban security. For example, to reveal potentially critical situations in an urban area, the incidence per city area/district/green area of the received requests for a given category/subcategory of emergency calls has been analyzed. To keep track of the temporal evolution of the citizen perception on urban security, the variation between two time periods in the number of calls received from a given city area/district/green area is analyzed. To avoid bias due to the unbalanced distribution of citizens per city area/district, the aforesaid KPIs are also normalized by the number of citizens per city area/district. 

The informative dashboard has been developed using SpagoBI Business Intelligence suite in the version 4.2 (available at www.ow2.org). KPIs are visualized using charts and maps. This directory contains the scripts to set up the informative dashboard realized using SpagoBI. The informative dashboard is released under MIT license. A web-based interface, described in directory [../webinterface](/webinterface), has been developed to visualize the informative dashboard.

## Installation

1. Install SpagoBI Server version 4.2 available at www.ow2.org. Install both GeoReportEngine and ChartEngine. 
2. Create and feed the repository storing the non emergency service requests (see directory [../datarepository](/datarepository))
3. Add the following configuration field to `webapps/SpagoBI/WEB_INF/web.xml`:
 
 ```xml
<resource-ref>
 
<description></description>
<res-ref-name>jdbc/data_warehouse</res-ref-name>
<res-type>javax.sql.DataSource</res-type>
<res-auth>Container</res-auth>
 
</resource-ref>
 ```
 
4. Add the following configuration field to `conf/server.xml`, providing the necessary information to connect to the database you set up in step 2.
 
  ```xml
<Resource name="jdbc/data_warehouse"  auth="Container"
type="javax.sql.DataSource" driverClassName=com.mysql.jdbc.Driver
url="jdbc:mysql://localhost:3306/Non_emergency_data_Torino_Unit"
username="root" password=""/>
  ```
5. Modify `META-INF/context.xml`  in **ALL** the webapps installed, adding the following field:
 
  ```xml
<ResourceLink global="jdbc/data_warehouse" name="jdbc/data_warehouse" type="javax.sql.DataSource"/>
 ```
 
6. To visualize KPIs on maps download, from a WFS, the shapefiles for city districts, city areas, and green areas.
Convert them to a GeoJSON format, with the use of a GIS software as QGIS, checking that that the projection system of the layer is the 'EPSG:4326'.  
In QGIS, dissolve
the field named “Ambito” in the green area (“Aree Verdi”) shape.  
Save the three files respectively to the following paths, in your SpagoBI server:
  * `resources/georeport/circoscrizioni_geo.json` (for city districts)
  * `resources/georeport/exquart.json` (for city areas)
  * `resources/georeport/green_spaces.json` (for city green areas)

7. Copy the file `spagobi.script` in the folder database.
8. Start up the server with the scripts in the bin folder (`bin/SpagoBIstartup.*`).

