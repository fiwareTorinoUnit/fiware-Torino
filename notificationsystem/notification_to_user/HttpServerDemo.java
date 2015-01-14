/*     Java page for the Notification to user
       - main class that run the server dispatcher of the notifications to users 
	   Copyright (c) 2014 Politecnico di Torino
       Released under MIT license. */
	   
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerDemo {
  public static void main(String[] args) throws IOException {
    InetSocketAddress addr = new InetSocketAddress(45555);
    HttpServer server = HttpServer.create(addr, 0); // 0 represents the backlog values, max incoming tcp connections

	/*create different contexts means create different urls*/
    server.createContext("/", new MyHandler()); 
	
    server.setExecutor(Executors.newCachedThreadPool());
    server.start();
    System.out.println("Server is listening on port 45555" );
  }
}