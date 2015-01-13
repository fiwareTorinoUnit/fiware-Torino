# FIWARE - Monitoring Non-Emergency Calls in Torino Smart City 

This repository contains the proof of concept software prototype developed by the Torino unit in the FIWARE project. It consists of an informative dashboard and a notification system to support the offline analyses of the non-emergency calls made by citizens to the Local Police’s Contact Centre in Torino. 

The informative dashboard displays a set of example Key Performance Indicators (KPIs) aimed at providing information on the citizens' perception on urban security, while the notification system allows monitoring the variation over time of this perception. The considered non-emergency calls dataset is available as open data on Torino Municipality Data Portal (http://aperto.comune.torino.it) and it has been made available on FIWARE Lab. 

The main objective of the project was to show possible ways to analyze the considered service requests, and to demonstrate facilities of [FIWARE](http://www.fi-ware.org/) platform for the targeted use case. The project is for prototyping purposes only. It is not production quality. 

## Requirements


The software prototype was created using the CAP (Context Awareness Platform) implementation of the Context Broker FIWARE GE and SpagoBI Business Intelligence suite. SpagoBI was installed in the version 4.2 and the GeoReport Engine 4.2 of SpagoBI has been used to display KPIs on top of geographical maps. Notification units were implemented in Java and integrated with the Context Broker FIWARE GE and SpagoBI Business Intelligence suites. Some additional Java based units have been developed to provide notification system functionalities.The data repository used for the offline analysis of non-emergency calls has been created using the open source RDBMS MySQL. The released code was tested on both CentOS and Windows 7 operating systems. 


## Content

This repository contains the following main directories:
* datarepository, containing the information on the dataset used for the analysis 
* informativedashboard, containing the scripts to set up the informative dashboard realized using SpagoBI
* webinterface, containing the web interface to visualize the informative dashboard
* notificationsystem, containing the software modules of the notification system


## Installation

Installation steps are provided within each directory

## License 
Software in this repository is licensed under the MIT license

## Contact 
Politecnico di Torino: silvia.chiusano@polito.it 


