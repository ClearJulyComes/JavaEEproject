package DBMethods;

import Entities.Login;
import Entities.MessageEntity;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class GetMessagesClass {
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(GetMessagesClass.class);
    private String userLogin;
    public GetMessagesClass(String userLogin){
        this.userLogin = userLogin;
    };

    public List<MessageEntity> getMessagesMethod(){
        List<MessageEntity> messages = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Login> query = entityManager.createQuery("SELECT f.hisFriend FROM Friends f WHERE " +
                "f.userLogin.userLogin = :userLoginParam", Login.class);
        query.setParameter("userLoginParam", userLogin);
        List<Login> myFriendsList = query.getResultList();
        TypedQuery<MessageEntity> queryMsg = entityManager.createQuery("SELECT m FROM MessageEntity m WHERE" +
                " m.userLogin.userLogin IN(:userLoginParam, :friendLogin) AND m.hisFriend.userLogin IN " +
                "(:userLoginParam, :friendLogin) ORDER BY m.messageId DESC", MessageEntity.class);
        queryMsg.setParameter("userLoginParam", userLogin);
        for(Login friend: myFriendsList){
            queryMsg.setParameter("friendLogin", friend.getUserLogin());
            queryMsg.setMaxResults(20);
            messages.addAll(queryMsg.getResultList());
        }
        logger.info("Method works");
        return messages;
    }
}
