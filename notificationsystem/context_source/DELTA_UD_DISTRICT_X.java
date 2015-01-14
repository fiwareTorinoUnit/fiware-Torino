/*     Java page for the Context_Source
       - class related to the KPI "delta Urban blight and renewal for each district" 
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class DELTA_UD_DISTRICT_X {
	
	public String SCOPE;
	public Timestamp TIMESTAMP;
	public String TS;
	public Timestamp EXPIRES;
	public String EX;
	public int x;
	public int size;
	
	public DELTA_UD_DISTRICT_X(int circo){
		TS = new String();
		EX = new String();
		this.SCOPE = "DELTA_UD_DISTRICT_"+circo+"";
		java.util.Date date= new java.util.Date();
	    this.TIMESTAMP = new Timestamp(date.getTime());
		int sec = 2592000; /* 30 days */
		Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(this.TIMESTAMP.getTime());
        cal.add(Calendar.SECOND, sec);
        this.EXPIRES = new Timestamp(cal.getTime().getTime());
		
		/* timestamp and expires formatting */
		this.TS = this.TIMESTAMP.toString();
		this.TS = this.TS.replaceAll("\\.","-07:00");
		this.TS = this.TS.replaceAll(" ","T");
		this.TS = this.TS.substring(0, this.TS.length() - 3);
		this.EX = this.EXPIRES.toString();
		this.EX = this.EX.replaceAll("\\.","-07:00");
		this.EX = this.EX.replaceAll(" ","T");
		this.EX = this.EX.substring(0, this.EX.length() - 3);
		this.x = circo;
	}
	
    public String obtain_kpi(int ID_entity) throws SQLException{ 
	   try
         {
		    int id_entita = ID_entity;
			String output = new String();
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finale_project_torino", "javauser", "javadude") ;
			System.out.println(".. doing Query");
			Statement stmt = conn.createStatement();
			String query_kpi = "select District, Area, ((count(*)-t2.n12)/t2.n12)*100 as delta from view_on_service_request as t1, (select Area as a12, count(*) as n12 from view_on_service_request where Six_Month = \"2013-1\" and Service_Name= \"Urban blight and renewal\" group by Area) as t2  where t1.Area = t2.a12 and Six_Month = \"2013-2\" and Service_Name = \"Urban blight and renewal\" and District="+this.x+" group by Area";
			System.out.print("District: ");
			System.out.println(this.x);
			ResultSet rs = stmt.executeQuery(query_kpi);
			output = build_kpi(rs,id_entita);
			return output;
		} 
    catch (ClassNotFoundException cnf) 
      {
        return "error_custom";
       }
    }
	
	public String build_kpi(ResultSet r, int id_entity) throws SQLException{
		int Entita = id_entity;
		String[] Quartieri = new String [10]; 
	    float[] Valori = new float[10];
	    String DataPart = new String();
	    this.size = 0 ;
		while(r.next()){
			Quartieri[this.size] = r.getString("Area");
			Valori[this.size] = r.getFloat("delta");
			this.size =  this.size + 1 ;
		}
		DataPart = create_datapart_2(Quartieri, Valori);	
		String final_message = new String();
		final_message = create_context_update(DataPart,Entita);
		return final_message;
	}
	
	public String create_datapart_2(String[] Q, float[] V){
		int i;
		String st ="Stable";
		String em ="Emergency";
		String pa ="Warning";
		String de ="Decrease";
		String LABEL = new String();
		String DP = new String();
		DP = "<dataPart>";
 		for	(i=0; i<this.size; i++){
			if( V[i] > 0 ){
				if( V[i] < 30 )
					LABEL = st;
				else {  if ( V[i] < 60 )
							LABEL = pa;
						else LABEL = em;
				}
			} else LABEL = de;
			/* DP = DP+"<par n=\""+Q[i]+"\">"+V[i]+"</par>"; */
			DP = DP+"<par n=\""+Q[i]+"\">"+LABEL+"</par>";	
		}
 		DP = DP+"</dataPart>";
		return DP;
	}
	
	public String create_context_update(String DATAPART, int ID_ENTITY){
		String mex = new String();
		mex = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><contextML><ctxEls><ctxEl><contextProvider id='MySource' v='0.0.0' /><entity id=\""+ID_ENTITY+"\" type=\"username\" /><scope>"+this.SCOPE+"</scope><timestamp>"+this.TS+"</timestamp><expires>"+this.EX+"</expires>"+DATAPART+"</ctxEl></ctxEls></contextML>";
		return mex;
	}
	
	public String getName(){
		return this.SCOPE;
	}
}