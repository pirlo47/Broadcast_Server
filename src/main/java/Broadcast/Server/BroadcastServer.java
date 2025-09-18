package Broadcast.Server;

import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BroadcastServer {
    public static void main(String[] args){

        //Register endpoints
        Set<Class<?>> endpointClasses = new HashSet<>();
        endpointClasses.add(BroadcastEndpoint.class);

        Server server = new Server("localhost", 8080, "/ws", null,  BroadcastEndpoint.class);

        try{
            server.start();
            System.out.println("Broadcast server started on ws://localhost:8080/ws/chat");
            System.out.println("Press enter to stop the server...");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            server.stop();
            System.out.println("Server stopped.");
        }
    }
}
