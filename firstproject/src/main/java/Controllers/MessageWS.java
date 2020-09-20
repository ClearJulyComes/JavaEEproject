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
    private static HashMap<Session, String> users = new HashMap<>();

    public MessageWS(){

    }

    @OnOpen
    public void onOpen (Session session, @PathParam("username") String username){
        chatEndpoints.add(this);
        users.put(session, username);
        logger.warn("COOOOOONNNNNNECTED");
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        logger.warn(message);
        try {
            JSONObject jsonObject = new JSONObject(message);
        logger.warn(jsonObject.getString("to") + " MESSSSSSSSSSSSSSSSSSSSSSSSages "
                + jsonObject.getString("from"));
        MessagesStore messagesStore = new MessagesStore(jsonObject.getString("from"),
                jsonObject.getString("to"), jsonObject.getString("message"));
        messagesStore.saveMessage();
        logger.warn("After save");
        Store store = new Store(messagesStore.getUserLogin(), messagesStore.getId());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(store);
        logger.warn("PPPPPPPPPPPPPPPPPPPPP Okii " + jsonString);
        session.getBasicRemote().sendText(jsonString);
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