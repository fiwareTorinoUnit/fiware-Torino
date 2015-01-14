/*     Java page for the Notification to user
       - class structured to contain the parameters of the KPIs received 
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

class DataPart {
	String[] rows;
	String[] Pars;
	String[] Values;
	int dim;
	
	public DataPart(String Data){
		this.rows = Data.split("</par>");
		this.dim = rows.length;
		this.Pars = new String[this.dim];
		this.Values = new String[this.dim];
		setPars(Data);
		setValues(Data);
	}
	public void setPars(String Data){
		int i;
		String[] temp = new String[3];
		for ( i = 0 ; i < this.dim ; i++ ){
			temp = this.rows[i].split("\"");
			this.Pars[i] = temp[1];
		}
	}
	public void setValues(String Data){
		int i;
		for ( i = 0 ; i < this.dim ; i++ ){
			this.Values[i] = this.rows[i].replaceAll(".*>" , "");
		}
	}
	public void print(){
		int i;
		for (i=0;i<this.dim;i++){
			System.out.print("Parameter: "+ this.Pars[i]+" - ");
			System.out.println("Value: "+ this.Values[i]);
		}
	}
}