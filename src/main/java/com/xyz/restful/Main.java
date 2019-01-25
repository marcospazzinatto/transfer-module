package com.xyz.restful;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.xyz.model.Account;
import com.xyz.model.LogTransaction;

public class Main {

	public static List<Account> accounts;
	public static List<LogTransaction> logTransactions ; 
	
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {
        System.out.println("Starting grizzly...");
        ResourceConfig rc = new PackagesResourceConfig("com.xyz.restful");
        return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
    }

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = startServer();
        System.out.println(String.format("WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI, BASE_URI));  
        
        System.in.read();
        httpServer.stop();
        
    }
}