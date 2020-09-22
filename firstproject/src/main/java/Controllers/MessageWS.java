package Controllers;

import DBMethods.MessagesStore;
import DBMethods.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/messages/{username}")
public class MessageWS {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MessageWS.class);
    private static Set<MessageWS> chatEndpoints
            = new CopyOnWriteArraySet<>();
    private static HashMap<String, Session> users = new HashMap<>();

    public MessageWS(){

    }

    @OnOpen
    public void onOpen (Session session, @PathParam("username") String username){
        chatEndpoints.add(this);
        users.put(username, session);
        logger.warn("COOOOOONNNNNNECTED");
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        logger.warn(message);
        JSONObject jsonObject = new JSONObject(message);
        try {
            if(jsonObject.getString("status").equals("send")) {
                logger.warn(jsonObject.getString("hisFriend") + " MESSSSSSSSSSSSSSSSSSSSSSSSages "
                        + jsonObject.getString("userLogin"));
                MessagesStore messagesStore = new MessagesStore(jsonObject.getString("userLogin"),
                        jsonObject.getString("hisFriend"), jsonObject.getString("msg"));
                messagesStore.saveMessage();
                logger.warn("After save");
                jsonObject.put("messageId", messagesStore.getId());
                logger.warn("Okii " + jsonObject.toString());
                session.getBasicRemote().sendText(jsonObject.toString());
                try {
                    users.get(jsonObject.getString("hisFriend")).getBasicRemote().sendText(jsonObject.toString());
                }catch (Exception e){
                    logger.warn("Exception: " + e);
                }
            }
        }catch (JSONException err){
            logger.warn("Error");
        }
    }

    @OnError
    public void onError(Throwable e){

    }

    @OnClose
    public void onClose(Session session){

    }
}