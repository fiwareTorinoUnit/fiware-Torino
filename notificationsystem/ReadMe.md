Notification system on non-emergency calls
------------------------------------------

The notification unit allows monitoring the variation of the citizens' perception on urban security over time in urban areas. Notifications related to specific service requests on a given urban area are selectively forwarded by email to some city actors. To generate notification messages, the system considers KPIs on the variation of the received service requests between consecutive time periods on city area/district/green area. Based on KPI values, the warning can be classified according to different severity levels (e.g., stable, substantial or critical increase, and decrease).

The notification system is based on the Context Awareness Platform (CAP) Context Broker FIWARE GE, a Publisher/Subscriber implementation. Three additional Java-based units support the CAP activity. Specifically, the subscription unit specifies to CAP the KPIs of interest for the city roles. The context source periodically provides updated KPI values to CAP. When a new KPI value matches one subscription condition, CAP notifies to the corresponding subscriber (i.e., city role) the KPI value. The third unit allows users to register into the notification system, by specifying their role and email address. This unit receives notifications from CAP for a given KPI value and city role, and then it forwards them to all the users registered into the system for that role. 

This repository contains the following main directories: 
* countext_source: it contains the unit to compute a selection of example KPIs values for the notification system and send them to the CAP Context Broker module
* subscription_system: it contains the role subscription unit that specifies to the CAP Context Broker the KPIs of interest for the city roles
* notification_to_user: it contains the unit that receives from the CAP Context Broker module the notifications for each role and forward them to the users registered with that role. Directory “notification_to_user/script_sql” contains the SQL scripts to create the schema of the tables for storing the information on the city role subscriptions for KPIs notification and on users registered to the notification system
* signup_notificationsystem: it contains a web-based system for user registration to the notification system and role subscription to KPIs
* keep_alive_system: based on the CAP Context Broker functionality, role subscriptions can expire after a given time interval; this module allows avoiding the role subscription expiration. 


### Installation

1. Install java JDK (1.6 or newer) on your OS
2. Install Java drivers that allow connection with MySql database 
   (Instructions available at http://dev.mysql.com/doc/connector-j/en/connector-j-installing-classpath.html)
3. Install Java drivers that allow using the mail protocols (smtp, pop3) 
   (Instructions available at: https://javamail.java.net/docs/README.txt)
4. To compile each unit of the notification system, proceed as follows
    * 4.1 Open Terminal 
    * 4.2 Move into each directory (i.e., directories “notification_to_user”, “countext_source”, “keep_alive_system”, and “subscription_system”)
    * 4.3 In each directory, compile with the instruction " javac *.java "
5. To create the database to store information on role subscription and user registration, move into directory “notification_to_user/script_sql” and run the SQL script.
6. To compile the web-based system to sign up to the notification system
    * 6.1 Copy directory "signup_notificationsystem" into the "www" directory of Apache installed on your CentOs.
    * 6.2 Open your browser and go to "localhost:8080/signup_notificationsystem /signup_form.php" to check if it works correctly.
    * 6.3 Purecss library has been used for the stylesheet.
7. To set up the SpagoBI environment for KPI visualization into the notification email, follows instructions reported in directory “./informativedashboard”
 
### Usage

Execution sequence (after activation of the CAP Context Broker module and population of the source dataset, i.e., the materialized view used for KPIs computation):
 
1. To run the role subscription unit, go into directory “subscription_system” and execute "java subscribe_roles" 
2. To run the context source unit, go into directory “context_source” and execute "java Source"
3. To run the unit forwarding notifications to user, go into directory “notification_to_user” and execute "java HttpServerDemo"
4. To run the keep alive system, go into directory “keep_alive_subscription_system” and execute "java keep_alive"  
5. To use the web-based system to sign up (directory “signup_notificationsystem”)
    * 5.1 Users that want to sign up have to connect to "signup_form.php" page 
and insert their own (Name, Surname, E-Mail) and choose their role within the System.
    * 5.2 Role subscriptions to KPIs can be added by connecting to "subscription.php" page, and selecting role, corresponding KPI and conditions for notification
