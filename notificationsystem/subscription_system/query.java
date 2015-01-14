/*     Java page for the Subscription system
       - class to query the db to obtain the couples Role-KPI  
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class query {
	
    public ResultSet distinct_subscriptions() throws SQLException{ 
	   ResultSet rs = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println(".. Ask to the database for all the subscriptions to do: ");
			Statement stmt = conn.createStatement();
			String query_kpi = "SELECT t1.ID_role AS Entity, t1.ID_kpi AS KPI_ID, t2.Kpi_name AS KPI_name FROM `roles_kpis` AS t1, kpis AS t2 WHERE t1.ID_kpi = t2.ID AND t1.id_sub IS NULL GROUP BY KPI_name,Entity" ;
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
	
	 public ResultSet distinct_labels(int KPI, int ROLE) throws SQLException{ 
	   ResultSet rs2 = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println(".. Asking for the labels: ");
			Statement stmt = conn.createStatement();
			String query_kpi = "SELECT t1.Condition as label FROM `roles_kpis` as t1 where ID_kpi="+KPI+" and ID_role="+ROLE+" " ;
			rs2 = stmt.executeQuery(query_kpi);
			return rs2;
		} 
		catch (ClassNotFoundException e){
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return rs2;	
    }
	
	 public ResultSet distinct_parameters(int KPI) throws SQLException{ 
	   ResultSet rs3 = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println("Asking for the parameters :");
			Statement stmt = conn.createStatement();
			String query_kpi = "SELECT Parameter FROM `kpi_parameters` where ID = "+KPI ;
		    rs3 = stmt.executeQuery(query_kpi);
			return rs3;
		}
		catch (ClassNotFoundException e){
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return rs3;
    }
	
	public int add_id(int id_sub, int Entity, int id_kpi) throws SQLException{ 
	   int affected_rows=0;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println("...Updating table of subscription...");
			Statement stmt = conn.createStatement();
			String query_kpi = "UPDATE roles_kpis SET id_sub="+id_sub+" WHERE ID_role="+Entity+" AND ID_kpi="+id_kpi+"" ;
		    affected_rows = stmt.executeUpdate(query_kpi);
			return affected_rows;
		}
		catch (ClassNotFoundException e){
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return affected_rows;
    }
	
}