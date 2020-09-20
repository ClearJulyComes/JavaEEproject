package DBMethods;

import Entities.Login;
import Entities.MessageEntity;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MessagesStore {
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddFriend.class);
    private String userLogin;
    private String hisFriend;
    private String msg;
    private long id;

    public MessagesStore(String userLogin, String hisFriend, String msg){
        this.userLogin = userLogin;
        this.hisFriend = hisFriend;
        this.msg = msg;
    }

    public void saveMessage(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Login userInfo = entityManager.find(Login.class, userLogin);
        Login hisFriendInfo = entityManager.find(Login.class, hisFriend);
        MessageEntity messageEntity = new MessageEntity(userInfo, hisFriendInfo, msg);
        entityManager.getTransaction().begin();
        entityManager.persist(messageEntity);
        entityManager.getTransaction().commit();
        Query query = entityManager.createQuery("SELECT m.messageId FROM MessageEntity m WHERE m.msg= :msg");
        query.setParameter("msg", msg);
        id = (Long) query.getSingleResult();
        entityManager.close();
    }

    public long getId(){
        return id;
    }
    public String getUserLogin(){
        return userLogin;
    }
}
