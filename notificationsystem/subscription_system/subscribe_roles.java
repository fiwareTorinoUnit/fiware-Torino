/*     Java page for the Subscription system
       - main class that is used to send the subscription for each role
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
import java.io.*;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
 
public class subscribe_roles {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
		subscribe_roles subscription_system = new subscribe_roles();
		query do_query = new query();  /* query object */
		
		String conds = new String(); /* subscription condition */
		String message_now = new String(); /* subscription string */
		
		/* subscriptions set */
		ResultSet subs = null;  
		/* for each sub, labels of interest (eg. emergency, warning, etc..) */
		ResultSet labels = null; 
		/* the parameters of an ATOMICSCOPE (eg. DELTA_DL_DISTRICT_1 = Crocetta, Centro) */
		ResultSet parameters = null; 
		
		int sub_id,i,j,k,size_subs,size_labels,size_params;
        
		/* query_1: groups with ENTITY - ID_KPI - NAME_KPI*/
		subs = do_query.distinct_subscriptions(); 
		
		/* store the result set */
		size_subs = sizeOfQuery(subs);
		int[] Entities = new int[size_subs];
		String[] KPIs = new String[size_subs];
		int[] KPIs_ID = new int[size_subs];
		int[] id_subs = new int[size_subs];
		
		/* for each tupla create a subscription */
		i=0;
		while (subs.next()){
			
			/* store the single couple 'ROLE - KPI' */
			Entities[i] = subs.getInt("Entity");
			KPIs[i] = subs.getString("KPI_name");
			KPIs_ID[i] = subs.getInt("KPI_ID");
			System.out.print("Entity " +Entities[i]+" - ");
			System.out.print("KPI " +KPIs[i]+" - ");
			System.out.println("ID_KPI " +KPIs_ID[i]);
			
			/* query_2: all the Labels for the single subscription */
			labels = do_query.distinct_labels(KPIs_ID[i],Entities[i]); 
			
			/* store the labels */
			size_labels = sizeOfQuery(labels);
			String[] Labels = new String[size_labels]; j=0;
			while(labels.next()){
				Labels[j] = labels.getString("label");
				System.out.println("Label: " +Labels[j]);
				j = j + 1 ;
			}
			
			/* query_3: parameters of interest of the KPI */
			parameters = do_query.distinct_parameters(KPIs_ID[i]); 
			
			/* store the parameters */
			size_params = sizeOfQuery(parameters);
			String[] Parameters = new String[size_params]; k=0;
			while(parameters.next()){
				Parameters[k] = parameters.getString("Parameter");
				System.out.println("Parameter: " +Parameters[k]);
				k = k + 1 ;
			}
			
			/* create CONDITION part of the message */
			conds = create_conditions (KPIs[i], Parameters, Labels);
			
			/* now create the whole sub_message that will be sent to CB */
			message_now = create_subscription(Entities[i],KPIs[i],conds); 
			
			System.out.println("Subscription Message Created!");
			System.out.println("Sending the following message...");
			
			/* send message obtaining the response ID for the keepAlive */
			sub_id = subscription_system.subscribe(message_now);
			id_subs[i] = sub_id;
			System.out.println("This is the sub ID : " +sub_id);
			int s = update_table_subs(sub_id,Entities[i],KPIs_ID[i],do_query);
			System.out.println("Updated rows: "+s+"  - number of different labels for the same KPI for the same Entity");
			i = i + 1;
		}
	}
 
	// HTTP POST request
	public int subscribe(String subscription) throws Exception {
		
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
 
		String urlParameters = subscription;
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		// System.out.println("Post parameters : " /*+ urlParameters*/);
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
		String[] temp = risposta.split("<subId>");
		String[] temp2 = temp[1].split("</subId>");
		String subID_string = temp2[0]; 
		int subID = Integer.parseInt(subID_string);
		return subID;
	}
	
	public static String create_subscription(int entity, String KPI, String conds){
		String mex = "cqlReq=<?xml version=\"1.0\" encoding=\"UTF-8\"?><contextQL><ctxQuery><action type=\"SUBSCRIBE\" /><entity>username|"+entity+"</entity><scope>"+KPI+"</scope><validity>18000</validity>"+conds+"</ctxQuery></contextQL>&callbackUrl=http://192.168.141.1:45555";
		return mex;
	}

	public static String create_conditions(String KPI,String[] param, String[] labels){
		int i,j;
		String conditions = new String();
		conditions = "<conds><cond type=\"ONVALUE\"><logical op=\"OR\">";
		for ( i = 0 ; i < param.length ; i++ ){
			for( j = 0 ; j < labels.length ; j++ ){
				conditions = conditions+"<constraint param=\""+KPI+"."+param[i]+"\" op=\"EQ\" value=\""+labels[j]+"\"/>";
			}
		}
		conditions = conditions+"</logical></cond></conds>";
		return conditions;
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
	
	public static int update_table_subs(int id_SUB, int Entity, int id_kpi, query do_q) {    
		int how_many_tuples;
		try {
			how_many_tuples = do_q.add_id(id_SUB, Entity, id_kpi);
			return how_many_tuples;
		} catch (SQLException e){return 0;}
		
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