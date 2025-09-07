package iuh.fit.se.bai06;

import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(
        value = "/chatEndpoint/{username}"
)
public class ChatEndpoint {
    
}
