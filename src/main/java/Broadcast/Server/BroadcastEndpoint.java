package Broadcast.Server;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chat")
public class BroadcastEndpoint {

    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        sessions.add(session);
        System.out.println("Client connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session sender){
        System.out.println("Message from " + sender.getId() + ":" + message);
        //Broadcast to all connected clients
        for (Session session: sessions){
            if (session.isOpen()){
                try{
                    session.getBasicRemote().sendText(message);
                }catch (IOException e) {
                    System.err.println("Failed to send message to" + session.getId());
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session,  Throwable throwable){
        sessions.remove(session);
        System.err.println("Error from client " + session.getId() + ": " + throwable.getMessage());
    }
}
