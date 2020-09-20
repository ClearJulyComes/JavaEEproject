package Controllers;

import DBMethods.AddFriend;
import DBMethods.DeleteFriend;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.postgresql.util.PSQLException;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ServerEndpoint("/friends/{username}")
public class FriendsWS {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FriendsWS.class);
    private static Set<FriendsWS> chatEndpoints
            = new CopyOnWriteArraySet<>();
    private static HashMap<String, Session> users = new HashMap<>();

    public FriendsWS(){

    }

    @OnOpen
    public void onOpen (Session session, @PathParam("username") String username){
        chatEndpoints.add(this);
        users.put(username, session);
        logger.warn("COOOOOONNNNNNECTED Friends");
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("username") String username) throws IOException {
        logger.warn(message);
        JSONObject valuesObject = new JSONObject();
        try {
            if(message.matches("^login=(.+)")) {
                logger.warn("if");
                Pattern pattern = Pattern.compile("login=(.+)");
                Matcher matcher = pattern.matcher(message);
                while (matcher.find()) {
                    message = matcher.group(1);
                }
                if(message.equals(username)){
                    throw new Exception("This is your login!");
                }
                logger.warn(message);
                AddFriend addFriend = new AddFriend(message, username);
                addFriend.addMethod();
                valuesObject.put("status", "added");
            }else{
                logger.warn("else");
                JSONObject jsonObject = new JSONObject(message);
                DeleteFriend friendship = new DeleteFriend(username, jsonObject.getString("userLogin"));
                friendship.deleteUserFriend();
                message = jsonObject.getString("userLogin");
                valuesObject.put("status", "deleted");
            }
            valuesObject.put("hisFriend", message);
            logger.warn(valuesObject.toString() + " string");
            session.getBasicRemote().sendText(valuesObject.toString());
            valuesObject.remove("hisFriend");
            valuesObject.put("hisFriend", username);
            logger.warn(valuesObject.toString() + " string");
            try {
                users.get(message).getBasicRemote().sendText(valuesObject.toString());
            }catch (Exception e){
                logger.warn("Exception: " + e);
            }
        }catch (JSONException err){
            logger.warn("Error");
        }catch (PSQLException msg){
            valuesObject.put("status", "errorDB");
            valuesObject.put("msg", "Some error with DB");
            session.getBasicRemote().sendText(valuesObject.toString());
        }catch (Exception msg){
            valuesObject.put("status", "errorUserLogin");
            valuesObject.put("msg", msg);
            session.getBasicRemote().sendText(valuesObject.toString());
        }
    }

    @OnError
    public void onError(Throwable e){

    }

    @OnClose
    public void onClose(Session session){

    }
}