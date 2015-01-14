/*     Java page for the keep alive system
       - class that query the db to obtain all the Subscription IDs that need to be alive
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class query {
	
    public ResultSet keep_alive() throws SQLException{ 
	   ResultSet rs = null;
	   try
         {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torino_users_and_roles_en", "javauser", "javadude") ;
			System.out.println(".. Obtaining the IDs of the Subscriptions");
			Statement stmt = conn.createStatement();
			String query_kpi = "SELECT distinct(id_sub) as id FROM `roles_kpis` where id_sub IS NOT NULL" ;
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