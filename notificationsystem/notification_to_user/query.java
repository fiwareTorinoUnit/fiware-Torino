/*     Java page for the Notification to user
       - class that implements all the query useful in that system
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class query {
	
    public ResultSet emails(int role) throws SQLException{ 
	   ResultSet rs = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println("..obtaining the mails");
			Statement stmt = conn.createStatement();
			String query_kpi = "SELECT Name, Surname, eMail, area FROM `users` WHERE `ID_role`="+role+"" ;
			rs = stmt.executeQuery(query_kpi);
			return rs;
		} 
		catch (ClassNotFoundException e){
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return rs;
    }
	
	public ResultSet areas(String KPI, String area) throws SQLException{ 
	   ResultSet rs = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println("..doing query for users that works on specific area");
			Statement stmt = conn.createStatement();
			String query_kpi = "select count(*) as quanti from kpis where Kpi_name='"+KPI+"' and District = (SELECT id_District FROM area_district as t1 WHERE t1.id="+area+")" ;
			rs = stmt.executeQuery(query_kpi);
			return rs;
		} 
		catch (ClassNotFoundException e){
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return rs;
    }
	
	public ResultSet dashboard_path(String KPI) throws SQLException{ 
	   ResultSet rs = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println("..doing query to extract the Spago Path");
			Statement stmt = conn.createStatement();
			String query_kpi = "select Path from kpis where Kpi_name='"+KPI+"'" ;
			rs = stmt.executeQuery(query_kpi);
			return rs;
		} 
		catch (ClassNotFoundException e){
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return rs;
    }
}