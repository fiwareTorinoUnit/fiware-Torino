/*     Java page for the keep alive system
       - main class that keep alive the subscription already done
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
import java.io.*;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.lang.*;
 
public class keep_alive {
 
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws Exception {
		
		String keep_alive_mex ="";
		ResultSet ids = null;
		query do_query = new query();
		int size,i;
		
		while (true) {
			/* ids to renew */
			ids = do_query.keep_alive();
			size = sizeOfQuery(ids);
			
			/* data structure to save ids */
			int[] ids_to_renew = new int[size];
		
			/* send the message for each id */
			i=0; while (ids.next()){
				ids_to_renew[i] = ids.getInt("id");
				keep_alive_mex = create_message(ids_to_renew[i]);
				keep_alive(keep_alive_mex);
				System.out.println(keep_alive_mex);
				i++;
			}
			Thread.sleep(10000);
		}
	}
 
	// HTTP POST request
	public static void keep_alive(String keep_alive_sub) throws Exception {
 
		String conf_path = "../conf.txt";
		String CB_path = CB_url(conf_path);
		String url = CB_path+"/ContextBroker/getContextQL";
		System.out.println("CB - "+url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
 
		String urlParameters = keep_alive_sub;
 
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
 
		// return subID
		String risposta = response.toString();
		System.out.println(risposta);
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
	
	public static String create_message(int sub_id){
		String mex = "cqlReq=<?xml version=\"1.0\" encoding=\"UTF-8\"?><contextQL><ctxQuery><action type=\"SUBSCRIBE\" /><validity>1000</validity><subId>"+sub_id+"</subId></ctxQuery></contextQL>";
		return mex;
	}

	public static int[] load_from_file(String path) { 
		ArrayList<Integer> sub_ids = new ArrayList<Integer>(); 
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				sub_ids.add(Integer.parseInt((String)sCurrentLine));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		int[] array= new int[sub_ids.size()];
		int i;
		for ( i = 0 ; i < sub_ids.size() ; i++ ){
			array[i] = (int)sub_ids.get(i);
		} return array;
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




