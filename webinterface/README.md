#Web-based interface for informative dashboard visualization 

This directory contains the web-based interface used to visualize the informative dashboard realized with SpagoBI (see directory [../informativedashboard](/informativedashboard)). 

The interface allows selecting the KPI to be visualized (i.e., for a give category/subcategory of emergency calls, the incidence or the variation of service requests between two time periods, possibly normalized by the number of citizens in the urban area), the spatial (city area/district/ green area) and temporal granularity for KPI computation, and how KPI should be visualized (using charts or on maps).



##Configuration

1.	Set up SpagoBI and the informative dashboard as explained in [../informativedashboard](/informativedashboard)
2.	If you have installed SpagoBI on a remote server, change the reference to it in the variable called spagoTarget, in [webinterface/js/utility.js](webinterface/js/utility.js#L89)

##Usage

Open `index.html` in a modern web browser.
