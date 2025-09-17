package Broadcast.Server;

import jakarta.websocket.*;

public class BroadcastEndpoint {

    @OnOpen
    public void onOpen(Session session){
        System.out.println("Client connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ":" + message);
        //Will add broadcast last
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session,  Throwable throwable){
        System.err.println("Error from client " + session.getId() + ": " + throwable.getMessage());

    }
}
