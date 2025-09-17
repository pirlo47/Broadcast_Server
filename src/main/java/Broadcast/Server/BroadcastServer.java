package Broadcast.Server;

import org.glassfish.tyrus.server.Server;

public class BroadcastServer {
    public static void main(String[] args){
        Server server = new Server(BroadcastEndpoint.class, "localhost", 8080, "/ws");

        try{
            server.start();
        }
    }
}
