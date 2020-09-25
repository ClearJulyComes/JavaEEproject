package Controllers;

import DBMethods.MessagesStore;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.json.JSONObject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

/**
 * Класс для работы с вебсокетом для сообщений. Сохраняет сообщения в БД и отправляет его второму пользователю.
 */
@ServerEndpoint("/messages/{username}")
public class MessageWS {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MessageWS.class);
    private static HashMap<String, Session> users = new HashMap<>();

    public MessageWS(){

    }

    @OnOpen
    public void onOpen (Session session, @PathParam("username") String username){
        users.put(username, session);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        JSONObject jsonObject = new JSONObject(message);
        try {
            if(jsonObject.getString("status").equals("send")) {
                MessagesStore messagesStore = new MessagesStore(jsonObject.getString("userLogin"),
                        jsonObject.getString("hisFriend"), jsonObject.getString("msg"));
                messagesStore.saveMessage();
                jsonObject.put("messageId", messagesStore.getId());
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