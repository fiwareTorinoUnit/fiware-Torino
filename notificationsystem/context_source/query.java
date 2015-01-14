/*     Java page for the Context_Source
       - class that query the db to obtain the KPI that will be formalized and sent  
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class query {
	
    public ResultSet roles(String KPI) throws SQLException{ 
	   ResultSet rs = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println(".. doing Query");
			Statement stmt = conn.createStatement();
			String query_kpi = "SELECT t1.ID_role AS Entity FROM `roles_kpis` AS t1, kpis AS t2 WHERE t1.ID_kpi = t2.ID AND t2.Kpi_name=\""+KPI+"\" GROUP BY KPI_name,Entity";
			System.out.println("...uploading KPI!");
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