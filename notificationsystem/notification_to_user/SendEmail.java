/*     Java page for the Notification to user
       - class for the creation of the structured mail for the user
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.util.Properties;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader; 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail {
	
	/* Parameters sent by mail */
	String kpi; 
	String descrizione;
	String date;
	String time;
	String status;
	String Spago_path;
	
	public SendEmail(String KPI, DataPart DP, String timestamp, String where, String descr, String path) throws IOException{
		int i;
		this.kpi = KPI;
		String[] d = timestamp.split("T");
		this.date = d[0];
		this.time = d[1]; 
		
		/*..using DataPart*/
		this.status = "<b>Status of the kpi "+where+":</b> <br>";
		for ( i = 0 ; i < DP.dim ; i++ ){
			this.status += DP.Pars[i] +" : "+DP.Values[i]+" <br>";
		}
		this.descrizione = descr;
		/* for url_dashboard, conf file contains spago main path */ 
		String conf_path="../conf.txt";
		this.Spago_path = Spago_url(conf_path);
		this.Spago_path = this.Spago_path+path;
	}
 
	public void to(String EMAIL, String NAME, String SURNAME) {
 
		/* we have to create an email ad-hoc */
		final String username = "marconard88@gmail.com";
		final String password = "tatung88123";
	
		System.out.println("..Next destination: "+ NAME+" "+SURNAME+" "+EMAIL);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("marconard88@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(EMAIL));
			message.setSubject("Notification of performance indicator");
			String mex_to_send = create_message(NAME,SURNAME,this.kpi,this.date,this.time,this.status,this.Spago_path);
			message.setContent(mex_to_send, "text/html; charset=utf-8");
 
			Transport.send(message);
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String create_message(String name, String surname, String kpi, String date, String time, String status, String Spago){
		String message = new String();
		String URL = "http://localhost:8080/SpagoBI/servlet/AdapterHTTP?PAGE=LoginPage&NEW_SESSION=TRUE&DIRECT_EXEC=TRUE&OBJECT_LABEL=";
		return message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd \"><html><head><title>CONTEXT AWARENESS PLATFORM</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta http-equiv=\"Content-Style-Type\" content=\"text/css\"></head><body><div style=\"height: 80px; \"><img src=\"http://oi59.tinypic.com/22dh1d.jpg\"></div><div style=\"text-align: left;\"><br><br> <b>RECIPIENT:</b> "+name+" "+surname+"<br><br><br> <b>KEY PERFORMANCE INDICATOR(KPI):</b> <i>"+kpi+"</i> <br><br><br><br>"+status+"<br><br><a href=\""+Spago+"\">Go to the dashboard</a></div></body></html>"; 
		/* 
			- lack of url to dashboard
		*/
	}
	
	public static String Spago_url(String path) throws IOException{
		FileReader fr =  new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		String[] lines = new String[2];
		String spago = new String("");
		
		int i;
		for (i=0; i<2; i++){
			lines[i] = textReader.readLine();
		}
		spago = lines[1];
		String[] spago_parts = spago.split("\"");
		return spago_parts[1];
	}
}