/*     Java page for the Context_Source
       - main class that run the source that formalize the KPIs sent to the Context Broker 
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

 
public class Source {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws IOException, SQLException {
		
		int[] check = new int[10];
		Source jsCB1 = new Source();
		Source jsCB2 = new Source();
		Source jsCB3 = new Source();
		Source jsCB4 = new Source();
		query query1 = new query();
		query query2 = new query();
		query query3 = new query();
		query query4 = new query();
		
		/* Annoying noises from public venues for each district */
		check[0] = delta_dist_loc_circo_x(jsCB1, query1);
		
		/* Annoying noises from public venues for the entire city */
		check[1] = delta_dist_loc_circos(jsCB2, query2);
		
		/* Urban decay for each district */
		check[2] = delta_urban_decay_circo_x(jsCB3, query3);
		
		/* Urban decay for the entire city */
		check[3] = delta_urban_decay_circos(jsCB4, query4);
		
	}
 
	// HTTP POST request
	private void ContextUpdate(String to_send) throws Exception {
 
		String conf_path = "../conf.txt";
		String CB_path = CB_url(conf_path);
		String url = CB_path+"/ContextBroker/contextUpdate";
		System.out.println("CB - "+url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type","text/xml");
 
		String urlParameters = to_send;
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
	}

	
	public static int delta_dist_loc_circo_x(Source s, query q){
		String[] KPI_DELTA_DL_CIRCO_X = new String[10];
		DELTA_DL_DISTRICT_X[] dl_circo_x = new DELTA_DL_DISTRICT_X[10];
		int i,j;
		System.out.println("KPI :  Annoying noises from public venues for each district");
		for ( i = 0; i < 10 ; i++ ){
			dl_circo_x[i] = new DELTA_DL_DISTRICT_X(i+1);
		}
		try {
			for ( i = 0 ; i < 10 ; i++ ){
				System.out.println("District No. "+(i+1));
				System.out.println(dl_circo_x[i].getName());
				ResultSet roles_involved = null;
				roles_involved = q.roles(dl_circo_x[i].getName());
				int size = sizeOfQuery(roles_involved);
				System.out.println(size);
				int[] r = new int[size];
				int k=0; 
				while (roles_involved.next()){
					r[k] = roles_involved.getInt("Entity");
					KPI_DELTA_DL_CIRCO_X[i]= dl_circo_x[i].obtain_kpi(r[k]);
					s.ContextUpdate(KPI_DELTA_DL_CIRCO_X[i]);
					k = k + 1 ;
				} roles_involved.beforeFirst();
			} return 1;
		} catch (Exception e) {
			System.out.println(e) ;
			return 0;
		}
	}
	
	
	public static int delta_dist_loc_circos(Source s, query q){
		String KPI_DELTA_DL_CIRCOS = new String();
		ResultSet roles_involved = null;
		DELTA_DL_DISTRICTS dl_circos = new DELTA_DL_DISTRICTS();
		System.out.println("KPI : Annoying noises from public venues for the entire city");
		try {
			roles_involved = q.roles(dl_circos.getName());
			int size = sizeOfQuery(roles_involved);
			int[] r = new int[size];
			int k=0; 
			while (roles_involved.next()){
				r[k] = roles_involved.getInt("Entity");
			    KPI_DELTA_DL_CIRCOS= dl_circos.obtain_kpi(r[k]);
				s.ContextUpdate(KPI_DELTA_DL_CIRCOS);
				k = k + 1 ;
			}
			return 1;
		} catch (Exception e) {
			System.out.println(e) ;
			return 0;
		}
	}
	
	
	public static int delta_urban_decay_circo_x(Source s, query q){
		String[] KPI_DELTA_UD_CIRCO_X = new String[10];
		DELTA_UD_DISTRICT_X[] ud_circo_x = new DELTA_UD_DISTRICT_X[10];
		int i,j;
		System.out.println("KPI : Urban decay for each district");
		for ( i = 0; i < 10 ; i++ ){
			ud_circo_x[i] = new DELTA_UD_DISTRICT_X(i+1);
		}
		try {
			for ( i = 0 ; i < 10 ; i++ ){
				System.out.println("District No"+(i+1));
				System.out.println(ud_circo_x[i].getName());
				ResultSet roles_involved = null;
				roles_involved = q.roles(ud_circo_x[i].getName());
				int size = sizeOfQuery(roles_involved);
				System.out.println(size);
				int[] r = new int[size];
				int k=0; 
				while (roles_involved.next()){
					r[k] = roles_involved.getInt("Entity");
					KPI_DELTA_UD_CIRCO_X[i]= ud_circo_x[i].obtain_kpi(r[k]);
					s.ContextUpdate(KPI_DELTA_UD_CIRCO_X[i]);
					k = k + 1 ;
				} roles_involved.beforeFirst();
			} return 1;
		} catch (Exception e) {
			System.out.println(e) ;
			return 0;
		}
	}
	
	
	public static int delta_urban_decay_circos(Source s, query q){
		String KPI_DELTA_UD_CIRCOS = new String();
		ResultSet roles_involved = null;
		DELTA_UD_DISTRICTS ud_circos = new DELTA_UD_DISTRICTS();
		System.out.println("KPI : Urban decay for the entire city");
		try {
			roles_involved = q.roles(ud_circos.getName());
			int size = sizeOfQuery(roles_involved);
			int[] r = new int[size];
			int k=0; 
			while (roles_involved.next()){
				r[k] = roles_involved.getInt("Entity");
			    KPI_DELTA_UD_CIRCOS= ud_circos.obtain_kpi(r[k]);
				s.ContextUpdate(KPI_DELTA_UD_CIRCOS);
				k = k + 1 ;
			}
			return 1;
		} catch (Exception e) {
			System.out.println(e) ;
			return 0;
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
	
	public static String CB_url(String path) throws IOException{
		FileReader fr =  new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		String[] lines = new String[2];
		String CB = new String("");
		
		int i;
		for (i=0; i<2; i++){
			lines[i] = textReader.readLine();
		}
		CB = lines[0];
		String[] CB_parts = CB.split("\"");
		return CB_parts[1];
	}
}
