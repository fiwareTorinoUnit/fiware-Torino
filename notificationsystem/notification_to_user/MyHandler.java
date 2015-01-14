/*     Java page for the Notification to user
       - class that implement the effective service offered by the server
       ( processing of the message received )   
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.sql.*;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.util.Scanner;

class MyHandler implements HttpHandler {
	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		query ask_db = new query();
		
		if (requestMethod.equalsIgnoreCase("POST")) {
			System.out.println("..Start processing body message");
		
			/* store the body of the POST message */
			InputStream body_stream = exchange.getRequestBody();
			String body_string = new Scanner(body_stream,"UTF-8").useDelimiter("\\A").next();
	  
			/* KPI received */
			String KPI = extract_kpi(body_string);
			System.out.println("1. this is the KPI: "+ KPI);
			
			/* path for spago dashboard */
			ResultSet path = null;
			String dash_path = new String("");
			try {
				path = ask_db.dashboard_path(KPI);
				while (path.next()){
					dash_path = path.getString("Path");
				}
			} catch (SQLException e){
				/* do something appropriate with the exception */
				e.printStackTrace();
			}
			
			String where=""; String descr = "";
			String comp1 = "DELTA_DL_DISTRICT_";
			String comp2 = "DELTA_UD_DISTRICT_";
			String comp3 = "DELTA_DL_DISTRICTS";
			String comp4 = "DELTA_UD_DISTRICTS";
			if (KPI.contains(comp1) || KPI.contains(comp2)){	where = "in the District Areas";  } 
			else {	where = "in the City";  } 
			if (KPI.contains(comp1) || KPI.equals(comp3)){ descr = "6-month variation for <i>Disturbance from public venues</i>"; } 
			else {	descr = "6-month variation for <i>Urban blight and renewal</i>";	} 
	  
			/* for which ROLE ? */
			int ROLE = extract_role(body_string); 
			ResultSet mails_db = null;
			System.out.println("2. this is the role (entity): "+ ROLE);
		
			/* extract Datapart */
			String DataP = new String();
			DataP = extract_DataPart(body_string);
			
			/* extract Timestamp */
			String timestamp = new String();
			timestamp = extract_timestamp(body_string);
			
			/* Print on screen the DataPart of the message to check */
			DataPart DP = new DataPart(DataP);
			DP.print();
			
			/* send mails to users */ 
			try{
				mails_db = ask_db.emails(ROLE);
				/* Store the values of the users involved (name, surname, mail) */
				int num_mails = sizeOfQuery(mails_db); int i=0;
				String[] mails = new String[num_mails];
				String[] names = new String[num_mails];
				String[] surnames = new String[num_mails];
				String[] areas = new String[num_mails];
				while(mails_db.next()){
					System.out.println("\n\n Mails that will receive the notification: ");
					areas[i] = mails_db.getString("area");
					if(areas[i].equals("0")){
						/*..do nothing */
 					} else { 
						ResultSet area = null;
						area = ask_db.areas(KPI,areas[i]);
						area.next();
						if (area.getString("quanti").equals("1")){/*..do nothing */} else continue;
					}
					names[i] = mails_db.getString("Name");
					surnames[i] = mails_db.getString("Surname");
					mails[i] = mails_db.getString("eMail");
					System.out.println("Name: "+names[i]+" Surname: "+surnames[i]+" Email: "+mails[i]);
					i++;
				}
				/* send mails to all the users associated with that role */
				send_mails_to_all(mails,names,surnames,KPI,DP,timestamp,where,descr,dash_path);
			}	
			catch (SQLException e){
				/* do something appropriate with the exception */
				e.printStackTrace();
			}
		}
	}
	
	public static int extract_role (String body){
		String[] sub1 = body.split("entity id=\"");
		String[] sub2 = sub1[1].split("\"");
		String Role = sub2[0];
		int id_role = Integer.parseInt(Role);
		return id_role;
	}
	
	public static String extract_kpi (String body){
		String[] sub1 = body.split("<scope>");
		String[] sub2 = sub1[1].split("</scope>");
		String kpi = sub2[0];
		return kpi;
	}
	
	public static String extract_DataPart(String body){
		String[] app1 = body.split("<dataPart>");
		String[] app2 = app1[1].split("</dataPart>");
		return app2[0];
	}
	
	public static String extract_timestamp(String body){
		String[] app1 = body.split("<timestamp>");
		String[] app2 = app1[1].split("</timestamp>");
		return app2[0];
	}
	
	public void send_mails_to_all(String[] emails, String[] names, String[] surnames, String KPI, DataPart dp, String timestamp, String where, String descr, String Spago) throws SQLException,IOException{
		
	    /* check what the server is sending via mails */
		System.out.println("Server is starting sending mails -----------");
		System.out.println("Kpi: "+KPI+ "- Description: "+descr);
		System.out.println("..sending following datapart:");
		dp.print();
		SendEmail new_mail = new SendEmail(descr,dp,timestamp,where,descr,Spago); int j;
		for( j = 0 ; j < emails.length ; j++){
			System.out.println("Mail No. "+(j+1));
			new_mail.to(emails[j],names[j],surnames[j]); 	  
		}
	}
	public static int sizeOfQuery(ResultSet rs){
		int dim =0;  
		try { 
				if (rs != null){  
				rs.beforeFirst();  
				rs.last();  
				dim = rs.getRow();  
			}	 
			rs.beforeFirst(); 
		} catch (SQLException e){}
		return dim;
    }    	
}